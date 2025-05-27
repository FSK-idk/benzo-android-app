package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoyaltyCardScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(LoyaltyCardScreenUiState())
    val uiState = _uiState.asStateFlow()
}