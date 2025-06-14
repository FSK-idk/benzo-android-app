package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzPaymentBottomSheet
import com.benzo.benzomobile.presentation.common.BzPaymentCard
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    paymentHistory: List<Payment>,
    onBackClick: () -> Unit,
) {
    var selectedPayment by rememberSaveable { mutableStateOf<Payment?>(null) }

    if (selectedPayment != null) {
        BzPaymentBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            payment = selectedPayment!!,
            onDismiss = { selectedPayment = null },
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "История платежей",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
                    onRefresh = onRefresh,
                    isRefreshing = isRefreshing,
                ) {
                    if (paymentHistory.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = "Здесь пока пусто. Заправьтесь!")
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(paymentHistory) { payment ->
                            BzPaymentCard(
                                modifier = Modifier.fillMaxWidth(),
                                payment = payment,
                                onClick = { selectedPayment = payment },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentHistoryScreenPreview() {
    val samplePayments = listOf(
        Payment(
            dateTime = ZonedDateTime.parse("2025-06-09T07:48:25.262574+10:00"),
            gasStation = GasStation(
                id = 4,
                address = "dom 123"
            ),
            stationId = 1,
            fuelType = FuelType.PETROL_95,
            fuelAmount = 413,
            carNumber = "A123XA12",
            paymentAmount = 100234,
            bonusesUsed = 12331,
            paymentKey = "nfr8792hbc97g287h3bc7168",
        ),
        Payment(
            dateTime = ZonedDateTime.parse("2025-06-09T07:48:25.262574+10:00"),
            gasStation = GasStation(
                id = 4,
                address = "dom 123"
            ),
            stationId = 1,
            fuelType = FuelType.PETROL_95,
            fuelAmount = 413,
            carNumber = "A123XA12",
            paymentAmount = 100234,
            bonusesUsed = 12331,
            paymentKey = "nfr8792hbc97g287h3bc7168",
        )
    )

    BenzoMobileTheme {
        PaymentHistoryScreen(
            modifier = Modifier,
            loadStatus = LoadStatus.Loaded,
            onRetry = {},
            isRetryAvailable = true,
            onRefresh = {},
            isRefreshing = false,
            snackbarHostState = SnackbarHostState(),
            paymentHistory = samplePayments,
            onBackClick = {},
        )
    }
}
