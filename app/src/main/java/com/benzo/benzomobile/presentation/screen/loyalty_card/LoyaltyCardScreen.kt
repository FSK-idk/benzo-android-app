package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzLoyaltyCard
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun LoyaltyCardScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    loyaltyCard: LoyaltyCard,
    login: String,
) {
    Scaffold(
        modifier = modifier,
        topBar = { BzTopAppBar(title = "Карта лояльности") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        when (loadStatus) {
            is LoadStatus.Loading -> {
                BzLoadingBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadStatus.Error -> {
                BzErrorBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    message = loadStatus.message,
                    onRetry = onRetry,
                    isRetryAvailable = isRetryAvailable,
                )
            }

            is LoadStatus.Loaded -> {
                BzPullToRefreshBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        BzLoyaltyCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            loyaltyCard = loyaltyCard,
                            login = login
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoyaltyCardScreenPreview() {
    BenzoMobileTheme {
        LoyaltyCardScreen(
            modifier = Modifier,
            loadStatus = LoadStatus.Loaded,
            onRetry = {},
            isRetryAvailable = true,
            onRefresh = {},
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            loyaltyCard = LoyaltyCard(
                number = "123456789",
                balance = 25000,
            ),
            login = "ivanivanov",
        )
    }
}