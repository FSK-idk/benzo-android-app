package com.benzo.benzomobile.presentation.screen.login

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.use_case.LoginUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onLoginChange(value: String) =
        _uiState.update { it.copy(login = value) }


    fun onPasswordChange(value: String) =
        _uiState.update { it.copy(password = value) }

    fun onPasswordVisibilityClick() =
        _uiState.update { it.copy(isPasswordShown = !it.isPasswordShown) }

    fun onLoginClicked(navigateNext: () -> Unit) =
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                loginUserUseCase(
                    login = _uiState.value.login,
                    password = _uiState.value.password,
                )
                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _uiState.update { it.copy(isLoading = false) }
                _uiState.value.snackbarHostState.showSnackbar(
                    message = "Network error",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
}