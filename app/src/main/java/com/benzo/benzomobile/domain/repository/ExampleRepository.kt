package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.ExampleModel

interface ExampleRepository {
    suspend fun getExampleModelList(): List<ExampleModel>
}