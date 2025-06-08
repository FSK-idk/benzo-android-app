package com.benzo.benzomobile.presentation.screen.settings

import com.benzo.benzomobile.domain.model.ThemeOption

data class SettingsScreenUiState(
    val isLoading: Boolean = true,
    val theme: ThemeOption = ThemeOption.SYSTEM,
)