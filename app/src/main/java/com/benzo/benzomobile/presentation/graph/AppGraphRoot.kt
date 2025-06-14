package com.benzo.benzomobile.presentation.graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.common.BzNavigationBar

fun NavGraphBuilder.appGraphRoot(
    onNavigateToLoginGraphRoot: () -> Unit,
) {
    composable<Destination.AppGraphRoot> {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BzNavigationBar(
                    onNavigateToProfileRoot = { currentDestination ->
                        navController.navigate(Destination.AppGraph.ProfileGraphRoot) {
                            launchSingleTop = true
                            popUpTo(currentDestination) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    onNavigateToStationsRoot = { currentDestination ->
                        navController.navigate(Destination.AppGraph.GasStationsGraphRoot) {
                            launchSingleTop = true
                            popUpTo(currentDestination) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    onNavigateToLoyaltyCardRoot = { currentDestination ->
                        navController.navigate(Destination.AppGraph.LoyaltyCardGraphRoot) {
                            launchSingleTop = true
                            popUpTo(currentDestination) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                )
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Destination.AppGraph.ProfileGraphRoot,
            ) {
                profileGraphRoot(
                    onNavigateToLoginGraphRoot = onNavigateToLoginGraphRoot,
                )

                gasStationsGraphRoot()

                loyaltyCardGraphRoot()
            }
        }
    }
}