package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun FuelAmountSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    fuelAmount: String,
    onFuelAmountChange: (String) -> Unit,
    fuelAmountError: String? = null,
    title: String,
) {
    val decimalFormatter = DecimalFormatter()

    OutlinedTextField(
        modifier = modifier,
        value = fuelAmount,
        suffix = { Text(text = "л.") },
        onValueChange = {
            onFuelAmountChange(decimalFormatter.cleanup(it))
        },
        isError = fuelAmountError != null,
        supportingText = {
            fuelAmountError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        label = { Text(text = title) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}