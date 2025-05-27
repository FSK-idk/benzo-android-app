package com.benzo.benzomobile.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.loyalty_card.loyaltyCardScreen

fun NavGraphBuilder.loyaltyCardGraphRoot() {
    composable<Destination.AppGraph.LoyaltyCardGraphRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.LoyaltyCardGraph.LoyaltyCardScreen,
        ) {
            loyaltyCardScreen()
        }
    }
}