package com.benzo.benzomobile.domain.use_case

class ValidateLoginUseCase {
    operator fun invoke(login: String): String? {
        val regex = Regex("^[a-zA-Z0-9_]+$")

        return when {
            login.isBlank() -> "Поле не может быть пустым"
            login.count() < 6 -> "Логин должен содержать хотя бы 6 символов"
            !regex.matches(login) -> "Можно использовать только буквы, цифры и нижнее подчёркивание"
            else -> null
        }
    }
}