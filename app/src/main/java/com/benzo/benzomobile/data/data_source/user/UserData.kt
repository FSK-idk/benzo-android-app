package com.benzo.benzomobile.data.data_source.user

import com.benzo.benzomobile.domain.model.GenderOption

data class UserData(
    val login: String,
    val name: String,
    val birthDate: String?,
    val carNumber: String?,
    val phoneNumber: String?,
    val email: String?,
    val gender: GenderOption?,
    val penalty: Int,
)