package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentHistoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = PaymentHistoryUiState(
            isLoading = false,
            payments = listOf(
                PaymentItem("08.06.2025", "АИ-95", "2000₽", "АЗС №1", "40 л"),
                PaymentItem("05.06.2025", "ДТ", "1800₽", "АЗС №2", "35 л")
            )
        )
    }
}
