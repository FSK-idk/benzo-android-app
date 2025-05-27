package com.benzo.benzomobile.presentation.screen.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onThemeSelected(theme: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTheme = theme,
            )
        }
    }
}