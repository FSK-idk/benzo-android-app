package com.benzo.benzomobile.presentation

import com.benzo.benzomobile.domain.model.ThemeOption

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data class Success(
        val themeOption: ThemeOption,
        val isAuthenticated: Boolean,
    ) : MainActivityUiState {
        override fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) =
            when (themeOption) {
                ThemeOption.SYSTEM -> isSystemDarkTheme
                ThemeOption.LIGHT -> false
                ThemeOption.DARK -> true
            }
    }

    fun shouldKeepSplashScreen() = this is Loading

    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}