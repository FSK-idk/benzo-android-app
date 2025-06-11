package com.benzo.benzomobile.presentation.screen.gas_stations

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.gasStationsScreen(
    onNavigateToStationsScreen: (GasStation) -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.GasStationsScreen> {
        Text("HI")
        val viewModel = koinViewModel<GasStationsScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        GasStationsScreen(
            isLoading = loadState.value.isLoading,
            isRefreshing = loadState.value.isRefreshing,
            gasStations = uiState.value.gasStations,
            snackbarHostState = loadState.value.snackbarHostState,
            onRefresh = viewModel::onRefresh,
            onGasStationClick = onNavigateToStationsScreen,
        )
    }
}