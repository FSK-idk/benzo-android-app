package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("login")
    val login: String,

    @SerialName("name")
    val name: String,

    @SerialName("birth_date")
    val birthDate: String?,

    @SerialName("car_number")
    val carNumber: String?,

    @SerialName("phone_number")
    val phoneNumber: String?,

    @SerialName("email")
    val email: String?,

    @SerialName("gender")
    val gender: String?,
)