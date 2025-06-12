package com.benzo.benzomobile.domain.use_case

class ValidateFuelAmountUseCase {
    operator fun invoke(fuelAmount: String): String? =
        when {
            fuelAmount.isBlank() -> "Не должно быть пустым"
            fuelAmount.toFloat() == 0.0f -> "Не должно равнятся нулю"
            else -> null
        }
}