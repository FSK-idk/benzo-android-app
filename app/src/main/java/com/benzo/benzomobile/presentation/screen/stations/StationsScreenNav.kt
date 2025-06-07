package com.benzo.benzomobile.presentation.screen.stations

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

// todo: rename to gasStations
fun NavGraphBuilder.stationsScreen() {
    composable<Destination.AppGraph.StationsGraph.StationsScreen> {

        val viewModel = koinViewModel<StationsScreenViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        StationsScreen(
            gasStations = uiState.value.gasStations,
            // todo: add lambda
            onGasStationClick = {},
        )
    }
}