package com.benzo.benzomobile.domain.use_case

class ValidateRepeatPasswordUseCase {
    operator fun invoke(password: String, repeatPassword: String): String? =
        when {
            password != repeatPassword -> "Пароли не совпадают"
            else -> null
        }
}