package com.benzo.benzomobile.domain.use_case

import java.text.NumberFormat

class ValidateFuelAmountUseCase {
    operator fun invoke(fuelAmount: String): String? =
        when {
            fuelAmount.isBlank() -> "Поле не может быть пустым"
            NumberFormat.getInstance().parse(fuelAmount)!!
                .toFloat() == 0.0f -> "Не должно равнятся нулю"

            else -> null
        }
}