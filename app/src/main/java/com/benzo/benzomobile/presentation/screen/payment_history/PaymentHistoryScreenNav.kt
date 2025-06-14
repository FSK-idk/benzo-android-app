package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.activity.compose.BackHandler
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

        BackHandler(onBack = onNavigateBack)

        PaymentHistoryScreen(
            loadStatus = loadState.value.loadStatus,
            onRetry =viewModel::onRetry,
            isRetryAvailable = loadState.value.isRetryAvailable,
            onRefresh = viewModel::onRefresh,
            isRefreshing = loadState.value.isRefreshing,
            snackbarHostState = loadState.value.snackbarHostState,
            paymentHistory = uiState.value.paymentHistory,
            onBackClick = onNavigateBack,
        )
    }
}
