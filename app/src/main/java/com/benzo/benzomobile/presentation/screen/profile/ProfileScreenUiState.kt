package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.material3.SnackbarHostState

data class ProfileScreenUiState(
    val id: Int = 0,
    val name: String = "",
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)