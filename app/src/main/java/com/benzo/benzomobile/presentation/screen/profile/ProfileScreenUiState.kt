package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.material3.SnackbarHostState
import com.benzo.benzomobile.R
import kotlinx.datetime.LocalDate

data class ProfileScreenUiState(
    val icon: Int = R.drawable.ic_launcher_foreground,
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val registrationDate: LocalDate = LocalDate.fromEpochDays(0),
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)