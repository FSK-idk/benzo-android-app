package com.benzo.benzomobile.data.storage.dto

import com.benzo.benzomobile.domain.model.User
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

val UserDto.toDomain
    get() = User(
        login = login,
        name = name,
        birthDate = birthDate,
        carNumber = carNumber,
        penalty = penalty,
    )