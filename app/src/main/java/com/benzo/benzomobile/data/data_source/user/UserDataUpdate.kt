package com.benzo.benzomobile.data.data_source.user

import com.benzo.benzomobile.domain.model.GenderOption

data class UserDataUpdate(
    val name: String,
    val birthDate: String,
    val carNumber: String,
    val phoneNumber: String,
    val email: String,
    val gender: GenderOption,
)
