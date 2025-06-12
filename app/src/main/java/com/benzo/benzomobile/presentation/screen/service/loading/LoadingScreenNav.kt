package com.benzo.benzomobile.presentation.screen.service.loading

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.loadingScreen() {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.LoadingScreen> {
        LoadingScreen()
    }
}