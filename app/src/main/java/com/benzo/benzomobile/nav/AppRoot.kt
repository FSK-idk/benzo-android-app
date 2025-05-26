package com.benzo.benzomobile.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.BottomNavigationBar

fun NavGraphBuilder.appRoot() {
    composable<Destination.AppRoot> {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    onNavigateToProfileRoot = { currentDestination ->
                        navController.navigate(Destination.ProfileRoot) {
                            launchSingleTop = true
                            popUpTo(currentDestination) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    onNavigateToStationsRoot = { currentDestination ->
                        navController.navigate(Destination.StationsRoot) {
                            launchSingleTop = true
                            popUpTo(currentDestination) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    onNavigateToLoyaltyCardRoot = { currentDestination ->
                        navController.navigate(Destination.LoyaltyCardRoot) {
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
                startDestination = Destination.ProfileRoot,
            ) {
                profileRoot()

                stationsRoot()

                loyaltyCardRoot()
            }
        }
    }
}