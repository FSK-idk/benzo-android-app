package com.benzo.benzomobile.presentation.screen.gas_station_stations

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.gasStationStationsScreen(
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.GasStationStationsScreen> {
        val destination =
            it.toRoute<Destination.AppGraph.GasStationsGraph.GasStationStationsScreen>()
        val viewModel = koinViewModel<GasStationStationsScreenViewModel> {
            parametersOf(
                GasStation(
                    id = destination.gasStationId,
                    address = destination.gasStationAddress
                )
            )
        }
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        GasStationStationsScreen(
            isLoading = loadState.value.isLoading,
            isRefreshing = loadState.value.isRefreshing,
            gasStation = uiState.value.gasStation,
            stations = uiState.value.stations,
            isTakeAvailable = uiState.value.isTakeAvailable,
            onTakeClick = { viewModel.onTakeClick(it, onNavigateNext) },
            snackbarHostState = loadState.value.snackbarHostState,
            onRefresh = viewModel::onRefresh,
            onBackClick = onNavigateBack,
        )
    }
}