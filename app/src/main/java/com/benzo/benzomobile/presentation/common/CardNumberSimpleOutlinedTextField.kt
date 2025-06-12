package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CardNumberSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardNumberError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = cardNumber,
        onValueChange = {
        val digits = it.filter { char -> char.isDigit() }
        if (digits.length <= 16) {
            onCardNumberChange(digits)
        }
    },
        label = { Text(text = title) },
        singleLine = true,
        visualTransformation = NumberVisualTransformation(
            mask = "#### #### #### ####",
            maskNumber = '#'
        ),
        isError = cardNumberError != null,
        supportingText = {
            cardNumberError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
    )
}

