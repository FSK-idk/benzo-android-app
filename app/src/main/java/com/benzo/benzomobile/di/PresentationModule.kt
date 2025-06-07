package com.benzo.benzomobile.di

import com.benzo.benzomobile.presentation.MainActivityViewModel
import com.benzo.benzomobile.presentation.screen.edit_profile.EditProfileScreenViewModel
import com.benzo.benzomobile.presentation.screen.login.LoginScreenViewModel
import com.benzo.benzomobile.presentation.screen.loyalty_card.LoyaltyCardScreenViewModel
import com.benzo.benzomobile.presentation.screen.profile.ProfileScreenViewModel
import com.benzo.benzomobile.presentation.screen.register.RegisterScreenViewModel
import com.benzo.benzomobile.presentation.screen.settings.SettingsScreenViewModel
import com.benzo.benzomobile.presentation.screen.stations.StationsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainActivityViewModel)
    viewModelOf(::RegisterScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::SettingsScreenViewModel)
    viewModelOf(::EditProfileScreenViewModel)
    viewModelOf(::LoyaltyCardScreenViewModel)
    viewModelOf(::StationsScreenViewModel)
}