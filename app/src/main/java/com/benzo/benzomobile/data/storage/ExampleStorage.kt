package com.benzo.benzomobile.data.storage

import com.benzo.benzomobile.data.storage.dto.ExampleDto

interface ExampleStorage {
    suspend fun getExampleDtoList(): List<ExampleDto>
}