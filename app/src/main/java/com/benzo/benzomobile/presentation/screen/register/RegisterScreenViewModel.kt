package com.benzo.benzomobile.presentation.screen.register

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.use_case.RegisterUseCase
import com.benzo.benzomobile.domain.use_case.ValidateConfirmPasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidateLoginUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterScreenViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onLoginChange(value: String) =
        _uiState.update {
            it.copy(
                login = value,
                loginError = validateLoginUseCase(value),
            )
        }

    fun onPasswordChange(value: String) =
        _uiState.update {
            it.copy(
                password = value,
                passwordError = validatePasswordUseCase(value),
            )
        }

    fun onConfirmPasswordChange(value: String) =
        _uiState.update {
            it.copy(
                confirmPassword = value,
                confirmPasswordError = validateConfirmPasswordUseCase(
                    password = it.password,
                    confirmPassword = value,
                ),
            )
        }

    fun onRegisterClicked(navigateNext: () -> Unit) {
        val loginError = validateLoginUseCase(_uiState.value.login)
        val passwordError = validatePasswordUseCase(_uiState.value.password)
        val confirmPasswordError = validateConfirmPasswordUseCase(
            password = _uiState.value.password,
            confirmPassword = _uiState.value.confirmPassword,
        )

        val hasErrors = listOf(
            loginError,
            passwordError,
            confirmPasswordError,
        ).any { it != null }

        if (hasErrors) {
            _uiState.update {
                it.copy(
                    loginError = loginError,
                    passwordError = passwordError,
                    confirmPasswordError = confirmPasswordError,
                )
            }
            return
        }

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                registerUseCase(
                    login = _uiState.value.login,
                    password = _uiState.value.password,
                )
                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _uiState.update { it.copy(isLoading = false) }
                _uiState.value.snackbarHostState.showSnackbar(
                    message = "Ошибка сети",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }
}

data class RegisterScreenUiState(
    val login: String = "",
    val loginError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)