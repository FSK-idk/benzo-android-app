package com.benzo.benzomobile.presentation.screen.register

data class RegisterScreenUiState(
    val login: String = "",
    val isLoginValidationError: Boolean = false,
    val loginValidationErrorMessage: String = "",
    val password: String = "",
    val isPasswordValidationError: Boolean = false,
    val passwordValidationErrorMessage: String = "",
    val confirmPassword: String = "",
    val isConfirmPasswordValidationError: Boolean = false,
    val confirmPasswordValidationErrorMessage: String = "",
)