package com.benzo.benzomobile.domain.use_case

import kotlinx.datetime.LocalDate

class ValidateBirthDateUseCase {
    operator fun invoke(birthDate: LocalDate?): String? =
        when (birthDate) {
            null -> "Укажите дату рождения"
            else -> null
        }
}