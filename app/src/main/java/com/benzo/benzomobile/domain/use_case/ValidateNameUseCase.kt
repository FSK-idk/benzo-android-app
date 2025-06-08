package com.benzo.benzomobile.domain.use_case

class ValidateNameUseCase {
    operator fun invoke(name: String): String? {
        val regex = Regex("^[a-zA-Zа-яА-ЯёЁ\\-\\s']+$")

        return when {
            name.isBlank() -> "Name cannot be blank"
            !regex.matches(name) -> "Only letters, spaces, hyphens, and apostrophes are allowed."
            else -> null
        }
    }
}