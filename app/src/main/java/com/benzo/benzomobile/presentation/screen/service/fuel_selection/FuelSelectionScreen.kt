package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.presentation.common.FuelAmountSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.FuelTypeSimpleOutlinedTextFiled
import com.benzo.benzomobile.presentation.common.MoneySimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelSelectionScreen(
    modifier: Modifier = Modifier,
    fuels: List<Fuel>,
    selectedFuelIndex: Int,
    onFuelTypeChange: (Int) -> Unit,
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
    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                title = "Выбор топлива",
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FuelTypeSimpleOutlinedTextFiled(
                modifier = Modifier.fillMaxWidth(),
                fuelTypes = fuels.map { it.type },
                selectedFuelTypeIndex = selectedFuelIndex,
                onFuelTypeChange = onFuelTypeChange,
                title = "Тип",
            )

            FuelAmountSimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                fuelAmount = fuelAmount,
                onFuelAmountChange = onFuelAmountChange,
                fuelAmountError = fuelAmountError,
                title = "Количество",
            )

            MoneySimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                money = paymentAmount,
                onMoneyChange = onPaymentAmountChange,
                moneyError = paymentAmountError,
                title = "Сумма",
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = "Цена: ${"%.2f".format(fuels[selectedFuelIndex].price / 100.0f)} руб.")
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onContinueClick,
            ) {
                Text(text = "Продолжить")
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


@Composable
@Preview
fun FuelSelectionScreenPreview() {
    BenzoMobileTheme {
        FuelSelectionScreen(
            fuels = listOf(
                Fuel(FuelType.PETROL_92, 1212),
                Fuel(FuelType.PETROL_95, 1212),
            ),
            selectedFuelIndex = 0,
            onFuelTypeChange = {},
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