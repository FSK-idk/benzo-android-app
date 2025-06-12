package com.benzo.benzomobile.presentation.screen.service.finish

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.finishScreen(
    onNavigateToGasStationsScreen: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen> {
        FinishScreen(
            onConfirmClick = onNavigateToGasStationsScreen
        )
    }
}