package com.benzo.benzomobile.presentation.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileScreenViewModel,
    onNavigateToSettingsScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
) {
    val icon = viewModel.icon.collectAsState()
    val name = viewModel.name.collectAsState()
    val phoneNumber = viewModel.phoneNumber.collectAsState()
    val email = viewModel.email.collectAsState()
    val registrationDate = viewModel.registrationDate.collectAsState()

    Scaffold { innerPadding ->
        ProfileScreen(
            modifier = Modifier.padding(innerPadding),
            icon = icon.value,
            name = name.value,
            phoneNumber = phoneNumber.value,
            email = email.value,
            registrationDate = registrationDate.value,
            onSettingsClick = onNavigateToSettingsScreen,
            onEditClick = onNavigateToEditProfileScreen,
            onExitClick = viewModel::onExitClick,
        )
    }
}