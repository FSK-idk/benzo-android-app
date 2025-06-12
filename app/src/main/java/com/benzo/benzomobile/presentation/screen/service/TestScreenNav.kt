package com.benzo.benzomobile.presentation.screen.service

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.FuelSelectionScreen
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.FuelSelectionScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.testScreen(
) {
    composable<Destination.AppGraph.GasStationsGraph.TestScreen> {
        val viewModel: ServiceViewModel = koinViewModel<ServiceViewModel>()

        val fuelSelectionScreenViewModel = koinViewModel<FuelSelectionScreenViewModel>()
        val fuelSelectionScreenUiState = fuelSelectionScreenViewModel
            .fuelSelectionScreenUiState.collectAsStateWithLifecycle()

        FuelSelectionScreen(
            fuels = fuelSelectionScreenUiState.value.fuels,
            selectedFuelIndex = fuelSelectionScreenUiState.value.selectedFuelIndex,
            onFuelTypeChange = fuelSelectionScreenViewModel::onFuelTypeChange,
            fuelAmount = fuelSelectionScreenUiState.value.fuelAmount,
            onFuelAmountChange = fuelSelectionScreenViewModel::onFuelAmountChange,
            fuelAmountError = fuelSelectionScreenUiState.value.fuelAmountError,
            paymentAmount = fuelSelectionScreenUiState.value.paymentAmount,
            onPaymentAmountChange = fuelSelectionScreenViewModel::onPaymentAmountChange,
            paymentAmountError = fuelSelectionScreenUiState.value.paymentAmountError,
            onCancelRefuelingClick = {
                viewModel.onCancelRefuelingClick()
            },
            onContinueClick = {
                fuelSelectionScreenViewModel.onContinueClick(
                    onNavigateNext = {

                    }
                )
            },
        )


//        val client: WebSocketClient = WebSocketClient("01")
//
//        TestScreen(
//            onStartClick = client::start,
//            onCancelRefuelingClick = client::sendCancelRefueling,
//            onSendClick = client::send,
//            onStopClick = client::stop,
//        )
    }
}