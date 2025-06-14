package com.benzo.benzomobile.presentation.screen.profile

import androidx.activity.compose.BackHandler
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
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = {})

        ProfileScreen(
            loadStatus = loadState.value.loadStatus,
            onRetry = viewModel::onRetry,
            isRetryAvailable = loadState.value.isRetryAvailable,
            onRefresh = viewModel::onRefresh,
            isRefreshing = loadState.value.isRefreshing,
            snackbarHostState = loadState.value.snackbarHostState,
            name = uiState.value.name,
            login = uiState.value.login,
            onHistoryClick = onNavigateToHistoryScreen,
            onEditProfileClick = onNavigateToEditProfileScreen,
            onSettingsClick = onNavigateToSettingsScreen,
            onExitClick = { viewModel.onExitClick(onNavigateToLoginGraphRoot) },
        )
    }
}