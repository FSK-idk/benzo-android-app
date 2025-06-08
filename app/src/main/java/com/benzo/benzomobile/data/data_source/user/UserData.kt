package com.benzo.benzomobile.data.data_source.user

data class UserData(
    val login: String,
    val name: String,
    val birthDate: String?,
    val carNumber: String?,
    val penalty: Int,
)