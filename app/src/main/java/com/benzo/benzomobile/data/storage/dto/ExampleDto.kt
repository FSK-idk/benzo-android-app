package com.benzo.benzomobile.data.storage.dto

import com.benzo.benzomobile.domain.model.ExampleModel

class ExampleDto(
    val text: String,
    val num: Float,
)

val ExampleDto.toDomain
    get() = ExampleModel(
        text = text,
        num = num
    )