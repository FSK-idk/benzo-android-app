package com.benzo.benzomobile.presentation.screen.service.payment_selection

import androidx.activity.compose.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.ServiceGraphViewModel

fun NavGraphBuilder.paymentSelectionScreen(
    viewModel: ServiceGraphViewModel,
    onCancelRefueling: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen> {
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = onNavigateBack)

        PaymentSelectionScreen(
            bonusesUsed = uiState.value.bonusesUsed,
            onBonusesUsedChange = viewModel::onBonusesUsedChange,
            bonusesAvailable = uiState.value.bonusesAvailable,
            cardNumber = uiState.value.cardNumber,
            onCardNumberChange = viewModel::onCardNumberChange,
            cardNumberError = uiState.value.cardNumberError,
            expirationDate = uiState.value.expirationDate,
            onExpirationDateChange = viewModel::onExpirationDateChange,
            expirationDateError = uiState.value.expirationDateError,
            holderName = uiState.value.holderName,
            onHolderNameChange = viewModel::onHolderNameChange,
            holderNameError = uiState.value.holderNameError,
            paymentAmount = uiState.value.paymentAmount,
            isPayAvailable = uiState.value.isPayAvailable,
            snackbarHostState = loadState.value.snackbarHostState,
            onBackClick = onNavigateBack,
            onCancelRefuelingClick = onCancelRefueling,
            onPayClick = { viewModel.onPayClick(onNavigateNext = onNavigateNext) },
        )
    }
}