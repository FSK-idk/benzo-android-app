package com.benzo.benzomobile.presentation.screen.payment_history

data class PaymentItem(
    val date: String,
    val fuelType: String,
    val price: String,
    val station: String,
    val volume: String
)

data class PaymentHistoryUiState(
    val isLoading: Boolean = false,
    val payments: List<PaymentItem> = emptyList()
)
