package com.benzo.benzomobile.presentation.screen.login

import androidx.compose.material3.SnackbarHostState

data class LoginScreenUiState(
    val login: String = "",
    val password: String = "",
    val isPasswordShown: Boolean = false,
    val isLoading: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)