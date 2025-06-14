package com.benzo.benzomobile.domain.model

import kotlinx.datetime.LocalDate

class User(
    val login: String,
    val name: String?,
    val birthDate: LocalDate?,
    val carNumber: String?,
    val phoneNumber: String?,
    val email: String?,
    val gender: GenderOption?,
)