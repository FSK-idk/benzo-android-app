package com.benzo.benzomobile.domain.use_case

class ValidatePasswordUseCase {
    operator fun invoke(password: String): String? {
        val doesContainLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        return when {
            password.isBlank() -> "Password cannot be blank"
            password.count() < 6 -> "Password must contain at least 6 symbols"
            !doesContainLettersAndDigits -> "Password must contain at least one letter and one digit"
            else -> null
        }
    }
}