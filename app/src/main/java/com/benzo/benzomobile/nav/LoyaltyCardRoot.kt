package com.benzo.benzomobile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.loyalty_card.LoyaltyCardScreenRoot
import com.benzo.benzomobile.presentation.loyalty_card.LoyaltyCardScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loyaltyCardRoot() {
    composable<Destination.LoyaltyCardRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.LoyaltyCardScreen,
        ) {
            loyaltyCardScreen()
        }
    }
}

fun NavGraphBuilder.loyaltyCardScreen() {
    composable<Destination.LoyaltyCardScreen> {
        val viewModel = koinViewModel<LoyaltyCardScreenViewModel>()
        // TODO: add state?
        LoyaltyCardScreenRoot(
            viewModel = viewModel,
        )
    }
}