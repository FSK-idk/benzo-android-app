package com.benzo.benzomobile.presentation.screen.service.payment_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzCardNumberOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzExpirationDateOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzHolderNameOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzOutlinedButton
import com.benzo.benzomobile.presentation.common.BzOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.presentation.common.DecimalFormatter
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.text.NumberFormat
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSelectionScreen(
    modifier: Modifier = Modifier,
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
    paymentAmount: String,
    isPayAvailable: Boolean,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onCancelRefuelingClick: () -> Unit,
    onPayClick: () -> Unit,
) {
    val decimalFormatter = DecimalFormatter()

    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Оплата",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Карта лояльности",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer,
            )

            BzOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Использовать бонусы",
                value = bonusesUsed,
                onValueChange = {
                    decimalFormatter.cleanup(it).let {
                        if (it.isBlank() || (
                                    NumberFormat.getInstance()
                                        .parse(it)!!
                                        .toFloat() * 100.0f).toInt() <= min(
                                bonusesAvailable,
                                (NumberFormat.getInstance()
                                    .parse(paymentAmount)!!
                                    .toFloat() * 100.0f).toInt(),
                            )
                        )
                            onBonusesUsedChange(it)
                    }
                },
                suffix = "руб.",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = "Доступно бонусов: ${"%.2f руб.".format(bonusesAvailable / 100.0f)}")
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Банковская карта",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer,
            )

            BzCardNumberOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Номер карты",
                value = cardNumber,
                onValueChange = onCardNumberChange,
                valueError = cardNumberError,
            )

            BzExpirationDateOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Срок действия",
                value = expirationDate,
                onValueChange = onExpirationDateChange,
                valueError = expirationDateError,
            )

            BzHolderNameOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Держатель",
                value = holderName,
                onValueChange = onHolderNameChange,
                valueError = holderNameError,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "К оплате: ${
                        "%.2f руб.".format(
                            NumberFormat.getInstance().parse(paymentAmount)!!.toFloat()
                                    - (
                                    if (bonusesUsed.isBlank()) 0f
                                    else NumberFormat.getInstance()
                                        .parse(bonusesUsed)!!.toFloat())
                        )
                    }"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BzButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onPayClick,
                text = "Оплатить",
                isAvailable = isPayAvailable,
            )

            BzOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCancelRefuelingClick,
                text = "Отменить заправку",
            )
        }
    }
}

@Composable
@Preview
fun PaymentSelectionScreenPreview() {
    BenzoMobileTheme {
        PaymentSelectionScreen(
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
            paymentAmount = "353.13",
            isPayAvailable = true,
            snackbarHostState = SnackbarHostState(),
            onBackClick = {},
            onCancelRefuelingClick = {},
            onPayClick = {},
        )
    }
}