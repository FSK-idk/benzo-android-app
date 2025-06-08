package com.benzo.benzomobile.domain.use_case

class ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): String? {
        val digitsOnly = phoneNumber.filter { it.isDigit() }
        val isValidChars = phoneNumber.all { it.isDigit() || it in "+-() " }

        return when {
            phoneNumber.isBlank() -> "Phone number cannot be blank"
            !isValidChars -> "Invalid characters"
            digitsOnly.length < 10 -> "Incorrect number"
            else -> null
        }
    }
}