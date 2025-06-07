package com.benzo.benzomobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.use_case.GetIsAuthenticatedUseCase
import com.benzo.benzomobile.domain.use_case.GetThemeOptionUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    getThemeOptionUseCase: GetThemeOptionUseCase,
    getIsAuthenticatedUseCase: GetIsAuthenticatedUseCase,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> =
        combine(
            getThemeOptionUseCase(),
            getIsAuthenticatedUseCase(),
        ) { themeConfig, isAuthenticated ->
            MainActivityUiState.Success(
                themeOption = themeConfig,
                isAuthenticated = isAuthenticated,
            )
        }
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                initialValue = MainActivityUiState.Loading,
                started = SharingStarted.WhileSubscribed(5000),
            )
}