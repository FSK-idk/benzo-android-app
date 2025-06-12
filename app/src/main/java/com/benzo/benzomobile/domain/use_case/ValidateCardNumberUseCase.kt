package com.benzo.benzomobile.domain.use_case

class ValidateCardNumberUseCase {
    operator fun invoke(cardNumber: String): String? {
        return when {
            cardNumber.isBlank() -> "Поле не может быть пустым"
            cardNumber.length < 16 -> "Неправильный формат"
            else -> null
        }
    }
}