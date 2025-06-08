package com.benzo.benzomobile.presentation.screen.loyalty_card

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

        LoyaltyCardScreen(
            isLoading = loadState.value.isLoading,
            isRefreshing = loadState.value.isRefreshing,
            loyaltyCard = uiState.value.loyaltyCard,
            login = uiState.value.login,
            snackbarHostState = loadState.value.snackbarHostState,
            onRefresh = viewModel::onRefresh,
        )
    }
}