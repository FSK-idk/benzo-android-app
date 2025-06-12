package com.benzo.benzomobile.domain.use_case

class ValidateExpirationDateUseCase {
    operator fun invoke(date: String): String? {
        val regex = Regex("(0[1-9]|1[0-2])")

        return when {
            date.isBlank() -> "Поле не может быть пустым"
            date.length < 2 -> "Неправильный формат"
            !regex.matches(date.substring(0, 2))-> "Неверный месяц"
            date.length < 4 -> "Неправильный формат"
            else -> null
        }
    }
}