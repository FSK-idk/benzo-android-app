package com.benzo.benzomobile.presentation.screen.service.payment_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.CardNumberSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.ExpirationDateSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.HolderNameSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.MoneySimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSelectionScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    bonusesUsed: String,
    onBonusesUsedChange: (String) -> Unit,
    bonusesAvailable: Int,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardNumberError: String?,
    expirationDate: String,
    onExpirationDateChange: (String) -> Unit,
    expirationDateError: String?,
    holderName: String,
    onHolderNameChange: (String) -> Unit,
    holderNameError: String?,
    paymentAmount: Int,
    isPayAvailable: Boolean,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onCancelRefuelingClick: () -> Unit,
    onPayClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                title = "Оплата",
                onBackClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Карта лояльности",
                    style = MaterialTheme.typography.titleMedium
                )

                MoneySimpleOutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    money = bonusesUsed,
                    onMoneyChange = {
                        if (it.isBlank() || (it.toFloat() * 100.0f).toInt() <= min(
                                bonusesAvailable,
                                paymentAmount
                            )
                        )
                            onBonusesUsedChange(it)
                    },
                    title = "Использовать бонусы"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(text = "Доступно бонусов: ${"%.2f".format(bonusesAvailable / 100.0f)} руб.")
                }

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "Банковская карта",
                    style = MaterialTheme.typography.titleMedium
                )

                CardNumberSimpleOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    cardNumber = cardNumber,
                    onCardNumberChange = onCardNumberChange,
                    cardNumberError = cardNumberError,
                    title = "Номер карты"
                )

                ExpirationDateSimpleOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    date = expirationDate,
                    onDateChange = onExpirationDateChange,
                    dateError = expirationDateError,
                    title = "Срок действия"
                )

                HolderNameSimpleOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    holderName = holderName,
                    onHolderNameChange = onHolderNameChange,
                    holderNameError = holderNameError,
                    title = "Держатель",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "К оплате: ${
                            "%.2f".format(
                                paymentAmount / 100.0f - (if (bonusesUsed.isBlank()) 0.0f else bonusesUsed.toFloat())
                            )
                        } руб."
                    )
                }

                Spacer(modifier = Modifier.weight(1.0f))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onPayClick,
                    enabled = isPayAvailable,
                ) {
                    Text(text = "Оплатить")
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelRefuelingClick,
                ) {
                    Text(text = "Отменить заправку")
                }
            }
        }
    }
}

@Composable
@Preview
fun PaymentSelectionScreenPreview() {
    BenzoMobileTheme {
        PaymentSelectionScreen(
            isLoading = false,
            bonusesUsed = "10.23",
            onBonusesUsedChange = {},
            bonusesAvailable = 12333,
            cardNumber = "1111222233334444",
            onCardNumberChange = {},
            cardNumberError = null,
            expirationDate = "",
            onExpirationDateChange = {},
            expirationDateError = null,
            holderName = "",
            onHolderNameChange = {},
            holderNameError = null,
            paymentAmount = 35313,
            isPayAvailable = true,
            snackbarHostState = SnackbarHostState(),
            onBackClick = {},
            onCancelRefuelingClick = {},
            onPayClick = {},
        )
    }
}