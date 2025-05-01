package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.storage.ExampleStorage
import com.benzo.benzomobile.data.storage.dto.ExampleDto
import com.benzo.benzomobile.data.storage.dto.toDomain
import com.benzo.benzomobile.domain.model.ExampleModel
import com.benzo.benzomobile.domain.repository.ExampleRepository

class ExampleRepositoryImpl(
    private val exampleStorage: ExampleStorage
) : ExampleRepository {
    override suspend fun getExampleModelList(): List<ExampleModel> {
        return exampleStorage.getExampleDtoList().map(ExampleDto::toDomain)
    }
}