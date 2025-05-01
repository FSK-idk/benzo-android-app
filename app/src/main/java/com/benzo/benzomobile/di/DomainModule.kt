package com.benzo.benzomobile.di

import com.benzo.benzomobile.domain.use_case.GetExampleModelListUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetExampleModelListUseCase) { bind<GetExampleModelListUseCase>() }
}