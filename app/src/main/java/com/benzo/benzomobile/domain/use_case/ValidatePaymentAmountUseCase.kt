package com.benzo.benzomobile.domain.use_case

import java.text.NumberFormat

class ValidatePaymentAmountUseCase {
    operator fun invoke(paymentAmount: String): String? =
        when {
            paymentAmount.isBlank() -> "Поле не должно быть пустым"
            NumberFormat.getInstance().parse(paymentAmount)!!
                .toFloat() == 0.0f -> "Не должно равнятся нулю"

            else -> null
        }
}