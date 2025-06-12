package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ExpirationDateSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    date: String,
    onDateChange: (String) -> Unit,
    dateError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = date,
        onValueChange = {
            val digits = it.filter { char -> char.isDigit() }
            if (digits.length <= 4) {
                onDateChange(digits)
            }
        },
        label = { Text(text = title) },
        singleLine = true,
        visualTransformation = NumberVisualTransformation(
            mask = "## / ##",
            maskNumber = '#'
        ),
        isError = dateError != null,
        supportingText = {
            dateError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
    )
}

