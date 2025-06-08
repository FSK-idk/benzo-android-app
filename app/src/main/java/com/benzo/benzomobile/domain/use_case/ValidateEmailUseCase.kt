package com.benzo.benzomobile.domain.use_case

class ValidateEmailUseCase {
    operator fun invoke(email: String): String? =
        when {
            email.isBlank() -> "Email cannot be blank"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Incorrect email"
            else -> null
        }
}