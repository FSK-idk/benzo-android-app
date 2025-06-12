package com.benzo.benzomobile.domain.use_case

class ValidatePasswordUseCase {
    operator fun invoke(password: String): String? {
        val doesContainLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        return when {
            password.isBlank() -> "Поле не может быть пустым"
            password.count() < 6 -> "Пароль должен содержать хотя бы 6 символов"
            !doesContainLettersAndDigits -> "Пароль должен сожержать хотя бы одну букву и одну цифру"
            else -> null
        }
    }
}