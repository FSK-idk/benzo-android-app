package com.benzo.benzomobile.di

import com.benzo.benzomobile.nav.DefaultNavigator
import com.benzo.benzomobile.nav.Destination
import com.benzo.benzomobile.nav.Navigator
import org.koin.dsl.module

val navModule = module {
    single<Navigator> {
        DefaultNavigator(Destination.ExampleScreen)
    }
}