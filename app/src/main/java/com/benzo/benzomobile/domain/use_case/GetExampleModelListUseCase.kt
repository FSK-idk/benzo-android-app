package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.ExampleModel
import com.benzo.benzomobile.domain.repository.ExampleRepository

class GetExampleModelListUseCase(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(): List<ExampleModel> {
        return exampleRepository.getExampleModelList()
    }
}