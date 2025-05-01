package com.benzo.benzomobile.di

import com.benzo.benzomobile.presentation.example.ExampleScreenViewModel
import com.benzo.benzomobile.presentation.example_second.ExampleSecondScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ExampleScreenViewModel)
    viewModelOf(::ExampleSecondScreenViewModel)
}