package com.benzo.benzomobile.domain.use_case

class ValidatePaymentAmountUseCase {
    operator fun invoke(paymentAmount: String): String? =
        when {
            paymentAmount.isBlank() -> "Поле не должно быть пустым"
            paymentAmount.toFloat() == 0.0f -> "Не должно равнятся нулю"
            else -> null
        }
}