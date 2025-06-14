package com.benzo.benzomobile.domain.model

enum class GenderOption {
    NONE,
    MALE,
    FEMALE,
}

fun getGenderName(gender: GenderOption) =
    when (gender) {
        GenderOption.NONE -> ""
        GenderOption.MALE -> "Мужской"
        GenderOption.FEMALE -> "Женский"
    }