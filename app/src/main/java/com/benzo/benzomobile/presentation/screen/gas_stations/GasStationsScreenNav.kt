package com.benzo.benzomobile.presentation.screen.gas_stations

import androidx.activity.compose.BackHandler
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
        val viewModel = koinViewModel<GasStationsScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = {})

        GasStationsScreen(
            loadStatus = loadState.value.loadStatus,
            onRetry = viewModel::onRetry,
            isRetryAvailable = loadState.value.isRetryAvailable,
            onRefresh = viewModel::onRefresh,
            isRefreshing = loadState.value.isRefreshing,
            onGasStationClick = onNavigateToStationsScreen,
            snackbarHostState = loadState.value.snackbarHostState,
            gasStations = uiState.value.gasStations,
            onSearch = viewModel::onSearch,
            isSearchAvailable = uiState.value.isSearchAvailable,
        )
    }
}