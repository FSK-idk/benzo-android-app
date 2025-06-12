package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun HolderNameSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    holderName: String,
    onHolderNameChange: (String) -> Unit,
    holderNameError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = holderName,
        onValueChange = { input ->
            val filtered = input
                .uppercase()
                .filter { it == ' ' || it in 'A'..'Z' }
            onHolderNameChange(filtered)
        },
        label = { Text(text = title) },
        singleLine = true,
        isError = holderNameError != null,
        supportingText = {
            holderNameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
    )
}
