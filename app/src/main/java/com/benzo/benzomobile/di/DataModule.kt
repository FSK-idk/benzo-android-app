package com.benzo.benzomobile.di

import com.benzo.benzomobile.data.repository.ExampleRepositoryImpl
import com.benzo.benzomobile.data.storage.ExampleStorage
import com.benzo.benzomobile.data.storage.example_model.ExampleStorageImpl
import com.benzo.benzomobile.domain.repository.ExampleRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::ExampleRepositoryImpl) { bind<ExampleRepository>() }
    singleOf(::ExampleStorageImpl) { bind<ExampleStorage>() }
}