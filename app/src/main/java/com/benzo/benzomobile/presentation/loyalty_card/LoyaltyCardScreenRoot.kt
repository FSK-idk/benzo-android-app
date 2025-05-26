package com.benzo.benzomobile.presentation.loyalty_card

import LoyaltyCardScreen
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoyaltyCardScreenRoot(
    viewModel: LoyaltyCardScreenViewModel,
) {
    val cardNumber = viewModel.cardNumber.collectAsState()
    val bonusesCount = viewModel.bonusesCount.collectAsState()
    val name = viewModel.name.collectAsState()
    val year = viewModel.year.collectAsState()

    Scaffold { innerPadding ->
        LoyaltyCardScreen(
            cardNumber = cardNumber.value,
            bonusesCount = bonusesCount.value,
            name = name.value,
            year = year.value,
        )
    }
}