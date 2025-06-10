package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel


fun NavGraphBuilder.paymentHistoryScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraph.PaymentHistoryScreen> {
        val viewModel = koinViewModel<PaymentHistoryViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        PaymentHistoryScreen(
            isLoading = loadState.value.isLoading,
            isRefreshing = loadState.value.isRefreshing,
            paymentHistory = uiState.value.paymentHistory,
            snackbarHostState = loadState.value.snackbarHostState,
            onRefresh = viewModel::onRefresh,
            onBackClick = onNavigateBack,
        )
    }
}
