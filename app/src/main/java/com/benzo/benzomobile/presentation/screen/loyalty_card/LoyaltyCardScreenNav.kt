package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loyaltyCardScreen() {
    composable<Destination.AppGraph.LoyaltyCardGraph.LoyaltyCardScreen> {
        val viewModel = koinViewModel<LoyaltyCardScreenViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            LoyaltyCardScreen(
                modifier = Modifier.padding(innerPadding),
                cardNumber = uiState.value.cardNumber,
                bonusesCount = uiState.value.bonusesCount,
                name = uiState.value.name,
                year = uiState.value.year,
            )
        }
    }
}