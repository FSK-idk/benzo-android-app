package com.benzo.benzomobile.domain.model

class User(
    val login: String,
    val name: String,
    val birthDate: String?,
    val carNumber: String?,
    val penalty: Int,
)