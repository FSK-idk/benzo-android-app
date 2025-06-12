package com.benzo.benzomobile.presentation.common

import androidx.compose.ui.text.input.OffsetMapping

class NumberOffsetMapper(
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
