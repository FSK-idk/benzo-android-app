package com.benzo.benzomobile.data.storage.example_model

import com.benzo.benzomobile.data.storage.ExampleStorage
import com.benzo.benzomobile.data.storage.dto.ExampleDto

class ExampleStorageImpl : ExampleStorage {
    override suspend fun getExampleDtoList(): List<ExampleDto> {
        return listOf()
    }
}