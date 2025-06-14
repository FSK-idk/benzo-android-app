package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzFuelTypeOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzOutlinedButton
import com.benzo.benzomobile.presentation.common.BzOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.presentation.common.DecimalFormatter
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelSelectionScreen(
    modifier: Modifier = Modifier,
    fuels: List<Fuel>,
    fuelIndex: Int,
    onFuelIndexChange: (Int) -> Unit,
    fuelAmount: String,
    onFuelAmountChange: (String) -> Unit,
    fuelAmountError: String?,
    paymentAmount: String,
    onPaymentAmountChange: (String) -> Unit,
    paymentAmountError: String?,
    snackbarHostState: SnackbarHostState,
    onCancelRefuelingClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    val decimalFormatter = DecimalFormatter()

    Scaffold(
        modifier = modifier,
        topBar = { BzTopAppBar(title = "Выбор топлива") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BzFuelTypeOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Тип",
                values = fuels.map { it.type },
                index = fuelIndex,
                onIndexChange = onFuelIndexChange,
            )

            BzOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Количество",
                value = fuelAmount,
                onValueChange = { onFuelAmountChange(decimalFormatter.cleanup(it)) },
                suffix = "л.",
                valueError = fuelAmountError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )

            BzOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Сумма",
                value = paymentAmount,
                onValueChange = { onPaymentAmountChange(decimalFormatter.cleanup(it)) },
                suffix = "руб.",
                valueError = paymentAmountError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = "Цена: ${"%.2f руб.".format(fuels[fuelIndex].price / 100.0f)}")
            }

            Spacer(modifier = Modifier.weight(1f))

            BzButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onContinueClick,
                text = "Продолжить",
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
fun FuelSelectionScreenPreview() {
    BenzoMobileTheme {
        FuelSelectionScreen(
            fuels = listOf(
                Fuel(FuelType.PETROL_92, 1212),
                Fuel(FuelType.PETROL_95, 1212),
            ),
            fuelIndex = 0,
            onFuelIndexChange = {},
            fuelAmount = "12.34",
            onFuelAmountChange = {},
            fuelAmountError = null,
            paymentAmount = "23.43",
            onPaymentAmountChange = {},
            paymentAmountError = null,
            snackbarHostState = SnackbarHostState(),
            onCancelRefuelingClick = {},
            onContinueClick = {},
        )
    }
}