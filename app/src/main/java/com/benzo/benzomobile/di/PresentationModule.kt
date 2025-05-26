package com.benzo.benzomobile.di

import com.benzo.benzomobile.presentation.edit_profile.EditProfileScreenViewModel
import com.benzo.benzomobile.presentation.example.ExampleScreenViewModel
import com.benzo.benzomobile.presentation.example_second.ExampleSecondScreenViewModel
import com.benzo.benzomobile.presentation.login.LoginScreenViewModel
import com.benzo.benzomobile.presentation.loyalty_card.LoyaltyCardScreenViewModel
import com.benzo.benzomobile.presentation.profile.ProfileScreenViewModel
import com.benzo.benzomobile.presentation.register.RegisterScreenViewModel
import com.benzo.benzomobile.presentation.settings.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::RegisterScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::SettingsScreenViewModel)
    viewModelOf(::EditProfileScreenViewModel)
    viewModelOf(::LoyaltyCardScreenViewModel)
    viewModelOf(::ExampleScreenViewModel)
    viewModelOf(::ExampleSecondScreenViewModel)
}