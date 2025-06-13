package com.benzo.benzomobile.presentation.screen.service.gas_nozzle_use

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.gasNozzleUseScreen() {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.GasNozzleUseScreen> {
        BackHandler(onBack = {})

        GasNozzleUseScreen()
    }
}