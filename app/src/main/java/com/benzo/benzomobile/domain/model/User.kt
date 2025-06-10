package com.benzo.benzomobile.domain.model

class User(
    val login: String,
    val name: String,
    val birthDate: String?,
    val carNumber: String?,
    val phoneNumber: String?,
    val email: String?,
    val gender: GenderOption?,
    val penalty: Int,
)