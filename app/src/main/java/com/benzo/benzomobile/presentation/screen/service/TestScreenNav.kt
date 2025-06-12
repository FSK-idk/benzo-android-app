package com.benzo.benzomobile.presentation.screen.service

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.FuelSelectionScreen
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.FuelSelectionScreenViewModel
import com.benzo.benzomobile.presentation.screen.service.payment_selection.PaymentSelectionScreen
import com.benzo.benzomobile.presentation.screen.service.payment_selection.PaymentSelectionScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.testScreen(
) {
    composable<Destination.AppGraph.GasStationsGraph.TestScreen> {
        val viewModel = koinViewModel<ServiceViewModel>()

        val paymentSelectionScreenViewModel = koinViewModel<PaymentSelectionScreenViewModel>()
        val paymentSelectionScreenLoadState = paymentSelectionScreenViewModel
            .loadState.collectAsStateWithLifecycle()
        val paymentSelectionScreenUiState = paymentSelectionScreenViewModel
            .uiState.collectAsStateWithLifecycle()

        PaymentSelectionScreen(
            bonusesUsed = paymentSelectionScreenUiState.value.bonusesUsed,
            onBonusesUsedChange = paymentSelectionScreenViewModel::onBonusesUsedChange,
            bonusesAvailable = paymentSelectionScreenUiState.value.bonusesAvailable,
            cardNumber = paymentSelectionScreenUiState.value.cardNumber,
            onCardNumberChange = paymentSelectionScreenViewModel::onCardNumberChange,
            cardNumberError = paymentSelectionScreenUiState.value.cardNumberError,
            expirationDate = paymentSelectionScreenUiState.value.expirationDate,
            onExpirationDateChange = paymentSelectionScreenViewModel::onExpirationDateChange,
            expirationDateError = paymentSelectionScreenUiState.value.expirationDateError,
            holderName = paymentSelectionScreenUiState.value.holderName,
            onHolderNameChange = paymentSelectionScreenViewModel::onHolderNameChange,
            holderNameError = paymentSelectionScreenUiState.value.holderNameError,
            paymentAmount = paymentSelectionScreenUiState.value.paymentAmount,
            isPayAvailable = paymentSelectionScreenUiState.value.isPayAvailable,
            snackbarHostState = paymentSelectionScreenLoadState.value.snackbarHostState,
            onBackClick = {

            },
            onCancelRefuelingClick = {
                viewModel.onCancelRefuelingClick()
            },
            onPayClick = {
                paymentSelectionScreenViewModel.onPayClick()
            },
        )

//        val fuelSelectionScreenViewModel = koinViewModel<FuelSelectionScreenViewModel>()
//        val fuelSelectionScreenUiState = fuelSelectionScreenViewModel
//            .fuelSelectionScreenUiState.collectAsStateWithLifecycle()
//
//        FuelSelectionScreen(
//            fuels = fuelSelectionScreenUiState.value.fuels,
//            selectedFuelIndex = fuelSelectionScreenUiState.value.selectedFuelIndex,
//            onFuelTypeChange = fuelSelectionScreenViewModel::onFuelTypeChange,
//            fuelAmount = fuelSelectionScreenUiState.value.fuelAmount,
//            onFuelAmountChange = fuelSelectionScreenViewModel::onFuelAmountChange,
//            fuelAmountError = fuelSelectionScreenUiState.value.fuelAmountError,
//            paymentAmount = fuelSelectionScreenUiState.value.paymentAmount,
//            onPaymentAmountChange = fuelSelectionScreenViewModel::onPaymentAmountChange,
//            paymentAmountError = fuelSelectionScreenUiState.value.paymentAmountError,
//            onCancelRefuelingClick = {
//                viewModel.onCancelRefuelingClick()
//            },
//            onContinueClick = {
//                fuelSelectionScreenViewModel.onContinueClick(
//                    onNavigateNext = {
//
//                    }
//                )
//            },
//        )


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