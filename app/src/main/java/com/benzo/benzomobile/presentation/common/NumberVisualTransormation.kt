package com.benzo.benzomobile.presentation.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NumberVisualTransformation(
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

        return TransformedText(maskedText, NumberOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        return other is NumberVisualTransformation &&
                other.mask == mask &&
                other.maskNumber == maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode() * 31 + maskNumber.hashCode()
    }
}
