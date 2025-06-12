package com.benzo.benzomobile.domain.use_case

class ValidateNameUseCase {
    operator fun invoke(name: String): String? {
        val regex = Regex("^[a-zA-Zа-яА-ЯёЁ\\-\\s']+$")

        return when {
            name.isBlank() -> "Поле не может быть пустым"
            !regex.matches(name) -> "Можно использовать только буквы, пробелы, тире и апострофы"
            else -> null
        }
    }
}