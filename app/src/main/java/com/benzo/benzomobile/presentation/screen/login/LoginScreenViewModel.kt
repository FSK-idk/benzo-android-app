package com.benzo.benzomobile.presentation.screen.login

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(LoginScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(LoginScreenUiState())
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
        _uiState.update { it.copy(login = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onPasswordVisibilityClick() {
        _uiState.update { it.copy(isPasswordShown = !it.isPasswordShown) }
    }

    fun onLoginClicked(navigateNext: () -> Unit) =
        viewModelScope.launch {
            _uiState.update { it.copy(isLoginAvailable = false) }

            try {
                loginUseCase(
                    login = _uiState.value.login,
                    password = _uiState.value.password,
                )

                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                sendMessage(message = e.message)
            }

            _uiState.update { it.copy(isLoginAvailable = true) }
        }
}

data class LoginScreenLoadState(
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class LoginScreenUiState(
    val login: String = "",
    val password: String = "",
    val isPasswordShown: Boolean = false,
    val isLoginAvailable: Boolean = true,
)