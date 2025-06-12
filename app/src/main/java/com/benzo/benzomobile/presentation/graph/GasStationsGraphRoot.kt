package com.benzo.benzomobile.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.gas_station_stations.gasStationStationsScreen
import com.benzo.benzomobile.presentation.screen.gas_stations.gasStationsScreen
import com.benzo.benzomobile.presentation.screen.service.serviceGraphRoot

fun NavGraphBuilder.gasStationsGraphRoot() {
    composable<Destination.AppGraph.GasStationsGraphRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.GasStationsGraph.GasStationsScreen,
        ) {
            gasStationsScreen(
                onNavigateToStationsScreen = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.GasStationStationsScreen(
                            gasStationId = it.id,
                            gasStationAddress = it.address
                        )
                    )
                }
            )

            gasStationStationsScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateNext = {
                    navController.navigate(Destination.AppGraph.GasStationsGraph.ServiceGraphRoot)
                }
            )

            serviceGraphRoot(
                onNavigateToGasStationsScreen = {
                    navController.navigate(Destination.AppGraph.GasStationsGraph.GasStationsScreen) {
                        popUpTo(Destination.AppGraph.GasStationsGraph.GasStationsScreen)
                    }
                }
            )
        }
    }
}