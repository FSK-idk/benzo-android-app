package com.benzo.benzomobile.di

import com.benzo.benzomobile.presentation.MainActivityViewModel
import com.benzo.benzomobile.presentation.screen.edit_profile.EditProfileScreenViewModel
import com.benzo.benzomobile.presentation.screen.gas_station_stations.GasStationStationsScreenViewModel
import com.benzo.benzomobile.presentation.screen.login.LoginScreenViewModel
import com.benzo.benzomobile.presentation.screen.loyalty_card.LoyaltyCardScreenViewModel
import com.benzo.benzomobile.presentation.screen.payment_history.PaymentHistoryViewModel
import com.benzo.benzomobile.presentation.screen.profile.ProfileScreenViewModel
import com.benzo.benzomobile.presentation.screen.register.RegisterScreenViewModel
import com.benzo.benzomobile.presentation.screen.settings.SettingsScreenViewModel
import com.benzo.benzomobile.presentation.screen.gas_stations.GasStationsScreenViewModel
import com.benzo.benzomobile.presentation.screen.service.ServiceGraphViewModel
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
    viewModelOf(::PaymentHistoryViewModel)
    viewModelOf(::GasStationsScreenViewModel)
    viewModelOf(::GasStationStationsScreenViewModel)
    viewModelOf(::ServiceGraphViewModel)
}
