package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PaymentAmountSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    paymentAmount: String,
    onPaymentAmountChange: (String) -> Unit,
    paymentAmountError: String? = null,
    title: String,
) {
    val decimalFormatter = DecimalFormatter()

    OutlinedTextField(
        modifier = modifier,
        value = paymentAmount,
        suffix = { Text(text = "руб.") },
        onValueChange = {
            onPaymentAmountChange(decimalFormatter.cleanup(it))
        },
        isError = paymentAmountError != null,
        supportingText = {
            paymentAmountError?.let {
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