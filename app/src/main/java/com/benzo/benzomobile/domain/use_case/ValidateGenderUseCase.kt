package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.GenderOption

class ValidateGenderUseCase {
    operator fun invoke(gender: GenderOption): String? =
        when {
            gender == GenderOption.NONE -> "Укажите пол"
            else -> null
        }
}