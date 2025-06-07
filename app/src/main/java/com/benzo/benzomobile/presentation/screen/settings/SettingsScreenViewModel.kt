package com.benzo.benzomobile.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.use_case.GetThemeOptionUseCase
import com.benzo.benzomobile.domain.use_case.SetThemeConfigUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val getThemeOptionUseCase: GetThemeOptionUseCase,
    private val setThemeConfigUseCase: SetThemeConfigUseCase,
) : ViewModel() {
    val uiState =
        getThemeOptionUseCase().map {
            SettingsScreenUiState(
                themeOption = it
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = SettingsScreenUiState(),
            started = SharingStarted.WhileSubscribed(5000),
        )

    fun onThemeSelected(themeOption: ThemeOption) {
        viewModelScope.launch {
            setThemeConfigUseCase(themeOption = themeOption)
        }
    }
}