package com.benzo.benzomobile.domain.use_case

class ValidateEmailUseCase {
    operator fun invoke(email: String): String? =
        when {
            email.isBlank() -> "Поле не может быть пустым"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Неправильный формат"
            else -> null
        }
}