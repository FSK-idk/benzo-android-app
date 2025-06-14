package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.activity.compose.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loyaltyCardScreen() {
    composable<Destination.AppGraph.LoyaltyCardGraph.LoyaltyCardScreen> {
        val viewModel = koinViewModel<LoyaltyCardScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = {})

        LoyaltyCardScreen(
            loadStatus = loadState.value.loadStatus,
            onRetry = viewModel::onRetry,
            isRetryAvailable = loadState.value.isRetryAvailable,
            onRefresh = viewModel::onRefresh,
            isRefreshing = loadState.value.isRefreshing,
            snackbarHostState = loadState.value.snackbarHostState,
            loyaltyCard = uiState.value.loyaltyCard,
            login = uiState.value.login,
        )
    }
}