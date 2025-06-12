package com.benzo.benzomobile.domain.use_case

class ValidateHolderNameUseCase {
    operator fun invoke(holderName: String): String? {
        return when {
            holderName.isBlank() -> "Поле не может быть пустым"
            else -> null
        }
    }
}