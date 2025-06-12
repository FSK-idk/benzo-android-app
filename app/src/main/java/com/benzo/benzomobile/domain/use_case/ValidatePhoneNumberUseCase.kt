package com.benzo.benzomobile.domain.use_case

class ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): String? {
        val digitsOnly = phoneNumber.filter { it.isDigit() }
        val isValidChars = phoneNumber.all { it.isDigit() || it in "+-() " }

        return when {
            phoneNumber.isBlank() -> "Поле не должно быть пустым"
            !isValidChars -> "Неправильный формат"
            digitsOnly.length < 10 -> "Неправильный формат"
            else -> null
        }
    }
}