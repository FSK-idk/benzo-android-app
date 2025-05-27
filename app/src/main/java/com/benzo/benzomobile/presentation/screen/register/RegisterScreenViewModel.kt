package com.benzo.benzomobile.presentation.screen.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterScreenUiState())
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

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirmPassword = value,
            )
        }
    }
}