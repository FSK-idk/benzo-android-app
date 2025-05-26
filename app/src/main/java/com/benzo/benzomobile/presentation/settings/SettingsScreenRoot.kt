package com.benzo.benzomobile.presentation.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreenRoot(
    viewModel: SettingsScreenViewModel,
    onNavigateBack: () -> Unit,
) {
    val selectedTheme = viewModel.selectedTheme.collectAsState()

    Scaffold { innerPadding ->
        SettingsScreen(
            modifier = Modifier.padding(innerPadding),
            selectedTheme = selectedTheme.value,
            onThemeSelected = viewModel::onThemeSelected,
            onBackClick = onNavigateBack,
        )
    }
}