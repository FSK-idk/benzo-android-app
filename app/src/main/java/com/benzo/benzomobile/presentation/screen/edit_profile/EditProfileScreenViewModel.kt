package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdate
import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
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
    private val fetchUserUseCase: FetchUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(EditProfileScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(EditProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchData()
        }

        viewModelScope.launch {
            _uiState.value = getUserUseCase()
                .filter {
                    when (it) {
                        is Result.Loading -> false
                        is Result.Success -> true

                        is Result.Error -> {
                            _loadState.value.snackbarHostState.showSnackbar(
                                message = it.message,
                                withDismissAction = true,
                                duration = SnackbarDuration.Short,
                            )
                            return@filter false
                        }
                    }
                }
                .filterIsInstance<Result.Success<User>>()
                .map {
                    _loadState.update { jt -> jt.copy(isLoading = false) }

                    EditProfileScreenUiState(
                        name = it.data.name,
                        birthDate = it.data.birthDate ?: "",
                        carNumber = it.data.carNumber ?: "",
                    )
                }
                .first()
        }
    }

    private suspend fun fetchData() =
        fetchUserUseCase()

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

                val result = updateUserUseCase(
                    userUpdate = UserUpdate(
                        name = _uiState.value.name,
                        carNumber = _uiState.value.carNumber,
                        birthDate = _uiState.value.birthDate,
                    )
                )

                when (result) {
                    is Result.Loading -> Unit

                    is Result.Success -> {
                        _loadState.value.snackbarHostState.showSnackbar(
                            message = "Successful",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short,
                        )
                    }

                    is Result.Error -> {
                        _loadState.value.snackbarHostState.showSnackbar(
                            message = result.message,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short,
                        )
                    }
                }
            }

            _loadState.update { it.copy(isSaveAvailable = true) }
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