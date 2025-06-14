package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzCardNumberOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    valueError: String? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = label) },
        value = value,
        onValueChange = {
            val digits = it.filter { char -> char.isDigit() }
            if (digits.length <= 16) {
                onValueChange(digits)
            }
        },
        singleLine = true,
        isError = valueError != null,
        supportingText = { valueError?.let { Text(text = it) } },
        visualTransformation = NumberVisualTransformation(
            mask = "#### #### #### ####",
            maskNumber = '#'
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@Preview
@Composable
private fun BzCardNumberOutlinedTextFieldPreview() {
    BenzoMobileTheme {
        Surface {
            BzCardNumberOutlinedTextField(
                modifier = Modifier,
                label = "label",
                value = "123",
                onValueChange = {},
                valueError = "error",
            )
        }
    }
}