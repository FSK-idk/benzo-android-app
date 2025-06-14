package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import androidx.activity.compose.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.ServiceGraphViewModel

fun NavGraphBuilder.fuelSelectionScreen(
    viewModel: ServiceGraphViewModel,
    onCancelRefueling: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen> {
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = onCancelRefueling)

        FuelSelectionScreen(
            fuels = uiState.value.fuels,
            fuelIndex = uiState.value.selectedFuelIndex,
            onFuelIndexChange = viewModel::onFuelTypeChange,
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