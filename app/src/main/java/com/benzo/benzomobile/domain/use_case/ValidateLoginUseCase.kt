package com.benzo.benzomobile.domain.use_case

class ValidateLoginUseCase {
    operator fun invoke(login: String): String? {
        val regex = Regex("^[a-zA-Z0-9_]+$")

        return when {
            login.isBlank() -> "Login can't be blank"
            login.count() < 6 -> "Login must contain at least 6 symbols"
            !regex.matches(login) -> "Only letters, digits and underscores are allowed"
            else -> null
        }
    }
}