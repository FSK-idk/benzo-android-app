package com.benzo.benzomobile.presentation.screen.profile

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.profileScreen(
    onNavigateToHistoryScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
    onNavigateToSettingsScreen: () -> Unit,
    onNavigateToLoginGraphRoot: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraph.ProfileScreen> {
        val viewModel = koinViewModel<ProfileScreenViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        ProfileScreen(
            id = uiState.value.id,
            name = uiState.value.name,
            snackbarHostState = uiState.value.snackbarHostState,
            onHistoryClick = onNavigateToHistoryScreen,
            onEditProfileClick = onNavigateToEditProfileScreen,
            onSettingsClick = onNavigateToSettingsScreen,
            onExitClick = { viewModel.onExitClick(onNavigateToLoginGraphRoot) },
        )
    }
}

