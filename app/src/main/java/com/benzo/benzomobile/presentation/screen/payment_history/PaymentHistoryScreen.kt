package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean,
    paymentHistory: List<Payment>,
    snackbarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
    onBackClick: () -> Unit,
) {
    var selectedPayment by rememberSaveable { mutableStateOf<Payment?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                title = "История платежей",
                onBackClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    itemsIndexed(paymentHistory) { index, payment ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedPayment = payment
                                    coroutineScope.launch { sheetState.show() }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Text(
                                        text = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                                            .format(payment.dateTime),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        getFuelTypeName(payment.fuelType),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    "%.2f ₽".format(payment.paymentAmount / 100.0f),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        if (index < paymentHistory.lastIndex) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                if (sheetState.isVisible && selectedPayment != null) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                selectedPayment = null
                            }
                        },
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = "Подробная информация",
                                fontWeight = FontWeight.Bold,
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                Text(
                                    text = "АЗС №${selectedPayment!!.gasStation.id} Колонка №${selectedPayment!!.stationId}"
                                )
                                Text(
                                    text = "Адрес: ${selectedPayment!!.gasStation.address}"
                                )
                                Text(
                                    text = "Топливо: ${getFuelTypeName(selectedPayment!!.fuelType)} ${
                                        "%.2f".format(
                                            selectedPayment!!.fuelAmount / 100.0f
                                        )
                                    } л"
                                )
                                Text(
                                    text = "Номер машины: ${selectedPayment!!.carNumber}"
                                )
                                Text(
                                    text = "Дата и время: ${
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")
                                            .format(selectedPayment!!.dateTime)
                                    }"
                                )
                                Text(
                                    text = "Сумма:  ${"%.2f".format(selectedPayment!!.paymentAmount / 100.0f)} ₽"
                                )
                                Text(
                                    text = "Использовано бонусов:  ${"%.2f".format(selectedPayment!!.bonusesUsed / 100.0f)}"
                                )
                                Text(
                                    text = "Ключ оплаты:  ${selectedPayment!!.paymentKey}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getFuelTypeName(fuelType: FuelType) =
    when (fuelType) {
        FuelType.PETROL_92 -> "АИ-92"
        FuelType.PETROL_95 -> "АИ-95"
        FuelType.PETROL_98 -> "АИ-98"
        FuelType.DIESEL -> "ДТ"
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
        )
    )

    BenzoMobileTheme {
        PaymentHistoryScreen(
            isLoading = false,
            isRefreshing = false,
            paymentHistory = samplePayments,
            snackbarHostState = SnackbarHostState(),
            onRefresh = {},
            onBackClick = {}
        )
    }
}
