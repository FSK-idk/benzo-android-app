package com.benzo.benzomobile.presentation.screen.service.finish

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.ServiceGraphViewModel

fun NavGraphBuilder.finishScreen(
    viewModel: ServiceGraphViewModel,
    onNavigateToGasStationsScreen: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen> {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        FinishScreen(
            finishMessage = uiState.value.finishMessage,
            onConfirmClick = onNavigateToGasStationsScreen
        )
    }
}