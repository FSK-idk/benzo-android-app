package com.benzo.benzomobile.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

fun NavGraphBuilder.stationsRoot() {
    composable<Destination.StationsRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.StationsScreen,
        ) {
            stationsScreen()
        }
    }
}

fun NavGraphBuilder.stationsScreen() {
    composable<Destination.StationsScreen> {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text("under maintenance")
        }
    }
}