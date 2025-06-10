package com.benzo.benzomobile.presentation.screen.payment_history

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(
    modifier: Modifier = Modifier,
    payments: List<PaymentItem>,
    onBackClick: () -> Unit,
) {
    var selectedPayment by remember { mutableStateOf<PaymentItem?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("История платежей") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                itemsIndexed(payments) { index, payment ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                selectedPayment = payment
                                coroutineScope.launch { sheetState.show() }
                            },
                        colors = cardColors,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(payment.date, style = MaterialTheme.typography.bodyMedium)
                                Text(payment.fuelType, fontWeight = FontWeight.Bold)
                            }
                            Text(payment.price, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    if (index < payments.lastIndex) {
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
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("АЗС: ${selectedPayment!!.station}")
                        Text("Объём: ${selectedPayment!!.volume}")
                        Text("Тип топлива: ${selectedPayment!!.fuelType}")
                        Text("Дата: ${selectedPayment!!.date}")
                        Text("Сумма: ${selectedPayment!!.price}")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PaymentHistoryScreenPreview() {
    val samplePayments = listOf(
        PaymentItem(date = "08.06.2025", fuelType = "АИ-95", price = "2000₽", station = "АЗС №1", volume = "40 л"),
        PaymentItem(date = "05.06.2025", fuelType = "ДТ", price = "1800₽", station = "АЗС №2", volume = "35 л"),
        PaymentItem(date = "03.06.2025", fuelType = "АИ-92", price = "1500₽", station = "АЗС №3", volume = "30 л")
    )

    BenzoMobileTheme {
        Surface {
            PaymentHistoryScreen(
                payments = samplePayments,
                onBackClick = {}
            )
        }
    }
}
