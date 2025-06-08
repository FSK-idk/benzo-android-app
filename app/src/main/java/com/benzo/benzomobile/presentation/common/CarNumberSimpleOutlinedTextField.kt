package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CarNumberSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    carNumber: String,
    onCarNumberChange: (String) -> Unit,
    carNumberError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = carNumber,
        onValueChange = { input ->
            val filtered = input
                .uppercase()
                .filter { it.isDigit() || it in 'А'..'Я' }
                .take(8)
            onCarNumberChange(filtered)
        },
        label = { Text(text = title) },
        singleLine = true,
        isError = carNumberError != null,
        supportingText = {
            carNumberError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
    )
}