package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.profileScreen(
    onNavigateToSettingsScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
    onNavigateToLoginGraphRoot: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraph.ProfileScreen> {
        val viewModel = koinViewModel<ProfileScreenViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = uiState.value.snackbarHostState,
                )
            }
        ) { innerPadding ->
            ProfileScreen(
                modifier = Modifier.padding(innerPadding),
                icon = uiState.value.icon,
                name = uiState.value.name,
                phoneNumber = uiState.value.phoneNumber,
                email = uiState.value.email,
                registrationDate = uiState.value.registrationDate,
                onSettingsClick = onNavigateToSettingsScreen,
                onEditClick = onNavigateToEditProfileScreen,
                onExitClick = { viewModel.onExitClick(onNavigateToLoginGraphRoot) },
            )
        }
    }
}

