package com.benzo.benzomobile.presentation.screen.gas_stations

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.gasStationsScreen() {
    composable<Destination.AppGraph.GasStationsGraph.GasStationsScreen> {
        val viewModel = koinViewModel<GasStationsScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        GasStationsScreen(
            isLoading = loadState.value.isLoading,
            isRefreshing = loadState.value.isRefreshing,
            gasStations = uiState.value.gasStations,
            snackbarHostState = loadState.value.snackbarHostState,
            onRefresh = viewModel::onRefresh,
            // TODO: add lambda
            onGasStationClick = {},
        )
    }
}