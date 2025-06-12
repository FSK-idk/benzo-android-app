package com.benzo.benzomobile.presentation.common

import java.text.DecimalFormatSymbols
import kotlin.text.iterator

class DecimalFormatter(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance(),
) {
    private val decimalSeparator = symbols.decimalSeparator

    fun cleanup(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()

        var hasDecimalSep = false
        var fracPart = 0

        for (char in input) {
            if (char.isDigit()) {
                if (hasDecimalSep) fracPart++
                if (fracPart > 2) break
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true
            }
        }

        return sb.toString()
    }
}