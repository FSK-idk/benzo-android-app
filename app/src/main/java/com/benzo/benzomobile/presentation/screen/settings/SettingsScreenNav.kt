package com.benzo.benzomobile.presentation.screen.settings

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.settingsScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraph.SettingsScreen> {
        val viewModel = koinViewModel<SettingsScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        SettingsScreen(
            isLoading = loadState.value.isLoading,
            selectedTheme = uiState.value.theme,
            onThemeClick = viewModel::onThemeSelected,
            onBackClick = onNavigateBack,
        )
    }
}