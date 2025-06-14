package com.benzo.benzomobile.presentation.screen.settings

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.LoadStatus
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
            .catch { e ->
                Log.e(TAG, "$e")
                sendMessage(message = e.message)
            }
            .map { theme ->
                _loadState.update { it.copy(loadStatus = LoadStatus.Loaded) }

                return@map SettingsScreenUiState(theme = theme)
            }.stateIn(
                scope = viewModelScope,
                initialValue = SettingsScreenUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )

    private fun sendMessage(message: String?) {
        viewModelScope.launch {
            _loadState.value.snackbarHostState.showSnackbar(
                message = message ?: "Ошибка",
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun onThemeSelected(theme: ThemeOption) {
        viewModelScope.launch {
            setThemeUseCase(theme = theme)
        }
    }
}

data class SettingsScreenLoadState(
    val loadStatus: LoadStatus = LoadStatus.Loading,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class SettingsScreenUiState(
    val theme: ThemeOption = ThemeOption.SYSTEM,
)