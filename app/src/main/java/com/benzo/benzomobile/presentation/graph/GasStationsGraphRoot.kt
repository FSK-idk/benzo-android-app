package com.benzo.benzomobile.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.gas_stations.gasStationsScreen

fun NavGraphBuilder.gasStationsGraphRoot() {
    composable<Destination.AppGraph.GasStationsGraphRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.GasStationsGraph.GasStationsScreen,
        ) {
            gasStationsScreen()
        }
    }
}