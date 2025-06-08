package com.benzo.benzomobile.domain.use_case

class ValidateBirthDateUseCase {
    operator fun invoke(birthDate: String): String? =
        when {
            birthDate.isBlank() -> "Select the date of birth"
            else -> null
        }
}