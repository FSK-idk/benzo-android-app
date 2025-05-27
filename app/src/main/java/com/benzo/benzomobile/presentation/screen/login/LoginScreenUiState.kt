package com.benzo.benzomobile.presentation.screen.login

data class LoginScreenUiState(
    val login: String = "",
    val isLoginValidationError: Boolean = false,
    val loginValidationErrorMessage: String = "",
    val password: String = "",
    val isPasswordValidationError: Boolean = false,
    val passwordValidationErrorMessage: String = "",
    val isPasswordShown: Boolean = false,
)