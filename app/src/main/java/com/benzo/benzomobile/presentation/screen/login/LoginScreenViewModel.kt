package com.benzo.benzomobile.presentation.screen.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onLoginChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                login = value,
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = value,
            )
        }
    }

    fun onPasswordVisibilityClick() {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordShown = !currentState.isPasswordShown,
            )
        }
    }
}