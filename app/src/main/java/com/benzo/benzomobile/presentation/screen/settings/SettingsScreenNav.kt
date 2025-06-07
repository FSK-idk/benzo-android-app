package com.benzo.benzomobile.presentation.screen.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            SettingsScreen(
                modifier = Modifier.padding(innerPadding),
                themeOption = uiState.value.themeOption,
                onThemeSelected = viewModel::onThemeSelected,
                onBackClick = onNavigateBack,
            )
        }
    }
}