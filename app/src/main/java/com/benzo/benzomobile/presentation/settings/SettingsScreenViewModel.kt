package com.benzo.benzomobile.presentation.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsScreenViewModel(

) : ViewModel() {
    // theme

    private val _selectedTheme = MutableStateFlow<String>("System")
    val selectedTheme = _selectedTheme.asStateFlow()

    // ui events

    fun onThemeSelected(theme: String) {
        _selectedTheme.value = theme
    }
}