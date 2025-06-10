package com.benzo.benzomobile.domain.model

data class UserUpdate(
    val name: String,
    val birthDate: String,
    val carNumber: String,
    val phoneNumber: String,
    val email: String,
    val gender: GenderOption,
)
