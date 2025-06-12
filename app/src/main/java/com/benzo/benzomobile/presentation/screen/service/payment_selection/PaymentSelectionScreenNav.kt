package com.benzo.benzomobile.presentation.screen.service.payment_selection

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.paymentSelectionScreen(
    onCancelRefueling: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateNext: (FuelSelectionResult) -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen> {
        val destination =
            it.toRoute<Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen>()

        val viewModel = koinViewModel<PaymentSelectionScreenViewModel> { parametersOf() }
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    }
}