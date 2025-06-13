package com.benzo.benzomobile.presentation.screen.edit_profile

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdateData
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.UpdateUserUseCase
import com.benzo.benzomobile.domain.use_case.ValidateBirthDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCarNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateEmailUseCase
import com.benzo.benzomobile.domain.use_case.ValidateGenderUseCase
import com.benzo.benzomobile.domain.use_case.ValidateNameUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePhoneNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileScreenViewModel(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateCarNumberUseCase: ValidateCarNumberUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateBirthDateUseCase: ValidateBirthDateUseCase,
    private val validateGenderUseCase: ValidateGenderUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(EditProfileScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(EditProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val user = getUserUseCase()

                _uiState.update {
                    it.copy(
                        name = user.name ?: "",
                        birthDate = user.birthDate ?: "",
                        carNumber = user.carNumber ?: "",
                        phoneNumber = user.phoneNumber ?: "",
                        email = user.email ?: "",
                        gender = user.gender ?: GenderOption.NONE,
                    )
                }

                _loadState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    fun onNameChange(value: String) =
        _uiState.update {
            it.copy(
                name = value,
                nameError = validateNameUseCase(value),
            )
        }

    fun onCarNumberChange(value: String) =
        _uiState.update {
            it.copy(
                carNumber = value,
                carNumberError = validateCarNumberUseCase(value),
            )
        }

    fun onPhoneNumberChange(value: String) {
        val digitsOnly = value.filter { it.isDigit() }

        _uiState.update {
            it.copy(
                phoneNumber = digitsOnly,
                phoneNumberError = validatePhoneNumberUseCase(digitsOnly),
            )
        }
    }

    fun onEmailChange(value: String) =
        _uiState.update {
            it.copy(
                email = value,
                emailError = validateEmailUseCase(value),
            )
        }

    fun onBirthDateChange(value: String) =
        _uiState.update {
            it.copy(
                birthDate = value,
                birthDateError = validateBirthDateUseCase(value),
            )
        }

    fun onGenderChange(value: GenderOption) =
        _uiState.update {
            it.copy(
                gender = value,
                genderError = validateGenderUseCase(value),
            )
        }

    fun setShowDatePicker(show: Boolean) =
        _uiState.update { it.copy(showDatePicker = show) }

    fun onSaveClick() {
        val lastNameError = validateNameUseCase(_uiState.value.name)
        val phoneNumberError = validatePhoneNumberUseCase(_uiState.value.phoneNumber)
        val emailError = validateEmailUseCase(_uiState.value.email)
        val birthDateError = validateBirthDateUseCase(_uiState.value.birthDate)
        val genderError = validateGenderUseCase(_uiState.value.gender)
        val carNumberError = validateCarNumberUseCase(_uiState.value.carNumber)

        val hasErrors = listOf(
            lastNameError,
            phoneNumberError,
            emailError,
            birthDateError,
            genderError,
            carNumberError
        ).any { it != null }

        _uiState.update {
            it.copy(
                nameError = lastNameError,
                phoneNumberError = phoneNumberError,
                emailError = emailError,
                birthDateError = birthDateError,
                genderError = genderError,
                carNumberError = carNumberError
            )
        }

        if (!hasErrors) {
            viewModelScope.launch {
                _loadState.update { it.copy(isSaveAvailable = false) }

                try {
                    updateUserUseCase(
                        userUpdateData = UserUpdateData(
                            name = _uiState.value.name,
                            carNumber = _uiState.value.carNumber,
                            birthDate = _uiState.value.birthDate,
                            phoneNumber = _uiState.value.phoneNumber,
                            email = _uiState.value.email,
                            gender = _uiState.value.gender,
                        )
                    )

                    _loadState.value.snackbarHostState.showSnackbar(
                        message = "Успешно сохранено",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "$e")
                    _loadState.value.snackbarHostState.showSnackbar(
                        message = e.message ?: "Ошибка",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                }

                _loadState.update { it.copy(isSaveAvailable = true) }
            }
        }
    }
}

data class EditProfileScreenLoadState(
    val isLoading: Boolean = true,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
    val isSaveAvailable: Boolean = true,
)

data class EditProfileScreenUiState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val birthDate: String = "",
    val birthDateError: String? = null,
    val gender: GenderOption = GenderOption.NONE,
    val genderError: String? = null,
    val showDatePicker: Boolean = false,
    val carNumber: String = "",
    val carNumberError: String? = null,
)