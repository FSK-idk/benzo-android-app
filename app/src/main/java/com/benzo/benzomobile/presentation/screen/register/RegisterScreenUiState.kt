package com.benzo.benzomobile.presentation.screen.register

import androidx.compose.material3.SnackbarHostState

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