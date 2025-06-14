package com.benzo.benzomobile.presentation.screen.register

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.use_case.RegisterUseCase
import com.benzo.benzomobile.domain.use_case.ValidateRepeatPasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidateLoginUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterScreenViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatPasswordUseCase: ValidateRepeatPasswordUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(RegisterScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(RegisterScreenUiState())
    val uiState = _uiState.asStateFlow()

    private fun sendMessage(message: String?) {
        viewModelScope.launch {
            _loadState.value.snackbarHostState.showSnackbar(
                message = message ?: "Ошибка",
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun onLoginChange(value: String) {
        _uiState.update {
            it.copy(
                login = value,
                loginError = validateLoginUseCase(value),
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                password = value,
                passwordError = validatePasswordUseCase(value),
            )
        }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                repeatPassword = value,
                repeatPasswordError = validateRepeatPasswordUseCase(
                    password = it.password,
                    repeatPassword = value,
                ),
            )
        }
    }

    fun onRegisterClicked(navigateNext: () -> Unit) {
        val loginError = validateLoginUseCase(_uiState.value.login)
        val passwordError = validatePasswordUseCase(_uiState.value.password)
        val confirmPasswordError = validateRepeatPasswordUseCase(
            password = _uiState.value.password,
            repeatPassword = _uiState.value.repeatPassword,
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
                    repeatPasswordError = confirmPasswordError,
                )
            }

            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isRegisterAvailable = false) }

            try {
                registerUseCase(
                    login = _uiState.value.login,
                    password = _uiState.value.password,
                )

                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                sendMessage(message = e.message)
            }

            _uiState.update { it.copy(isRegisterAvailable = true) }
        }
    }
}

data class RegisterScreenLoadState(
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class RegisterScreenUiState(
    val login: String = "",
    val loginError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: String? = null,
    val isRegisterAvailable: Boolean = true,
)