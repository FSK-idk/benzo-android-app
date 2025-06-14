package com.benzo.benzomobile.domain.model

import kotlinx.datetime.LocalDate

data class UserUpdateData(
    val name: String,
    val birthDate: LocalDate,
    val carNumber: String,
    val phoneNumber: String,
    val email: String,
    val gender: GenderOption,
)
