package com.benzo.benzomobile.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.use_case.GetThemeUseCase
import com.benzo.benzomobile.domain.use_case.SetThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(SettingsScreenLoadState())
    val loadState = _loadState.asStateFlow()

    val uiState =
        getThemeUseCase()
            .map {
                _loadState.update { jt -> jt.copy(isLoading = false) }

                return@map SettingsScreenUiState(theme = it)
            }.stateIn(
                scope = viewModelScope,
                initialValue = SettingsScreenUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )

    fun onThemeSelected(theme: ThemeOption) {
        viewModelScope.launch {
            setThemeUseCase(theme = theme)
        }
    }
}

data class SettingsScreenLoadState(
    val isLoading: Boolean = true,
)

data class SettingsScreenUiState(
    val theme: ThemeOption = ThemeOption.SYSTEM,
)