package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("login")
    val login: String,

    @SerialName("name")
    val name: String,

    @SerialName("birth_date")
    val birthDate: String?,

    @SerialName("car_number")
    val carNumber: String?,

    @SerialName("penalty")
    val penalty: Int,
)