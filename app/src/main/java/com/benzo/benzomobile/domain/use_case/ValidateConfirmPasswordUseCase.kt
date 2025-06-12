package com.benzo.benzomobile.domain.use_case

class ValidateConfirmPasswordUseCase {
    operator fun invoke(password: String, confirmPassword: String): String? =
        when {
            password != confirmPassword -> "Пароли не совпадают"
            else -> null
        }
}