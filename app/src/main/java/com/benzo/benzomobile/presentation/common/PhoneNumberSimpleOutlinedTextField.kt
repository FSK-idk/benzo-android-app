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
fun PhoneNumberSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    phoneNumberError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = phoneNumber,
        onValueChange = {
        val digits = it.filter { char -> char.isDigit() }
        if (digits.length <= 10) {
            onPhoneNumberChange(digits)
        }
    },
        label = { Text(text = title) },
        singleLine = true,
        visualTransformation = PhoneVisualTransformation(
            mask = "+7 (###) ###-##-##",
            maskNumber = '#'
        ),
        isError = phoneNumberError != null,
        supportingText = {
            phoneNumberError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
    )
}

class PhoneVisualTransformation(
    private val mask: String,
    private val maskNumber: Char,
) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(maxLength)

        val maskedText = buildAnnotatedString {
            var textIndex = 0
            for (maskChar in mask) {
                if (maskChar == maskNumber) {
                    if (textIndex < trimmed.length) {
                        append(trimmed[textIndex])
                        textIndex++
                    } else {
                        break
                    }
                } else {
                    append(maskChar)
                }
            }
        }

        return TransformedText(maskedText, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        return other is PhoneVisualTransformation &&
                other.mask == mask &&
                other.maskNumber == maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode() * 31 + maskNumber.hashCode()
    }
}

private class PhoneOffsetMapper(
    val mask: String,
    val numberChar: Char,
) : OffsetMapping {
    private val firstInputPosition = mask.indexOf(numberChar)

    override fun originalToTransformed(offset: Int): Int {
        var digitsSeen = 0
        var transformedOffset = 0

        while (digitsSeen < offset && transformedOffset < mask.length) {
            if (mask[transformedOffset] == numberChar) {
                digitsSeen++
            }
            transformedOffset++
        }

        while (transformedOffset < mask.length && mask[transformedOffset] != numberChar) {
            transformedOffset++
        }

        return transformedOffset.coerceAtLeast(firstInputPosition)
    }

    override fun transformedToOriginal(offset: Int): Int {
        val digits = mask.take(offset).count { it == numberChar }
        return digits
    }
}
