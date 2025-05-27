package com.benzo.benzomobile.presentation.screen.loyalty_card

data class LoyaltyCardScreenUiState(
    val cardNumber: String = "",
    val bonusesCount: Int = 0,
    val name: String = "",
    val year: Int = 0,
)