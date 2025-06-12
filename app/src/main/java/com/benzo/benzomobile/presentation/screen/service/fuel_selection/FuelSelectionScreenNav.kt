package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.fuelSelectionScreen(
    onCancelRefueling: () -> Unit,
    onNavigateNext: (FuelSelectionResult) -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen> {
        val destination =
            it.toRoute<Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen>()

        val viewModel = koinViewModel<FuelSelectionScreenViewModel> { parametersOf(destination.stationId) }
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        FuelSelectionScreen(
            isLoading = loadState.value.isLoading,
            fuels = uiState.value.fuels,
            selectedFuelIndex = uiState.value.selectedFuelIndex,
            onFuelTypeChange = viewModel::onFuelTypeChange,
            fuelAmount = uiState.value.fuelAmount,
            onFuelAmountChange = viewModel::onFuelAmountChange,
            fuelAmountError = uiState.value.fuelAmountError,
            paymentAmount = uiState.value.paymentAmount,
            onPaymentAmountChange = viewModel::onPaymentAmountChange,
            paymentAmountError = uiState.value.paymentAmountError,
            snackbarHostState = loadState.value.snackbarHostState,
            onCancelRefuelingClick = onCancelRefueling,
            onContinueClick = { viewModel.onContinueClick(onNavigateNext = onNavigateNext) },
        )
    }
}