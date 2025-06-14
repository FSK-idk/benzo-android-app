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
fun BzHolderNameOutlinedTextField(
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
        onValueChange = { input ->
            val filtered = input
                .uppercase()
                .filter { it == ' ' || it in 'A'..'Z' }
            onValueChange(filtered)
        },
        singleLine = true,
        isError = valueError != null,
        supportingText = { valueError?.let { Text(text = it) } },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@Preview
@Composable
private fun BzHolderNameOutlinedTextFieldPreview() {
    BenzoMobileTheme {
        Surface {
            BzHolderNameOutlinedTextField(
                modifier = Modifier,
                label = "label",
                value = "value",
                onValueChange = {},
                valueError = "error",
            )
        }
    }
}