package com.benzo.benzomobile.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.benzo.benzomobile.data.repository.AuthenticationRepositoryImpl
import com.benzo.benzomobile.data.repository.ThemeRepositoryImpl
import com.benzo.benzomobile.data.repository.UserRepositoryImpl
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user.UserDataSource
import com.benzo.benzomobile.data.data_source.user.UserDataSourceImpl
import com.benzo.benzomobile.data.data_source.authentication.AuthenticationDataSource
import com.benzo.benzomobile.data.data_source.authentication.AuthenticationDataSourceImpl
import com.benzo.benzomobile.data.data_source.gas_station.GasStationDataSource
import com.benzo.benzomobile.data.data_source.gas_station.GasStationDataSourceImpl
import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSource
import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSourceImpl
import com.benzo.benzomobile.data.data_source.payment_history.PaymentHistoryDataSource
import com.benzo.benzomobile.data.data_source.payment_history.PaymentHistoryDataSourceImpl
import com.benzo.benzomobile.domain.model.UserPreferences
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSourceImpl
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesSerializer
import com.benzo.benzomobile.data.repository.GasStationRepositoryImpl
import com.benzo.benzomobile.data.repository.LoyaltyCardRepositoryImpl
import com.benzo.benzomobile.data.repository.PaymentHistoryRepositoryImpl
import com.benzo.benzomobile.domain.repository.AuthenticationRepository
import com.benzo.benzomobile.domain.repository.GasStationRepository
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import com.benzo.benzomobile.domain.repository.PaymentHistoryRepository
import com.benzo.benzomobile.domain.repository.ThemeRepository
import com.benzo.benzomobile.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::BenzoApi) { bind<BenzoApi>() }

    singleOf(::UserPreferencesSerializer) { bind<UserPreferencesSerializer>() }
    single<DataStore<UserPreferences>> {
        DataStoreFactory.create(
            serializer = get<UserPreferencesSerializer>()
        ) {
            androidContext().dataStoreFile("user_preferences.pb")
        }
    }

    singleOf(::UserPreferencesDataSourceImpl) { bind<UserPreferencesDataSource>() }
    singleOf(::AuthenticationDataSourceImpl) { bind<AuthenticationDataSource>() }
    singleOf(::UserDataSourceImpl) { bind<UserDataSource>() }
    singleOf(::LoyaltyCardDataSourceImpl) { bind<LoyaltyCardDataSource>() }
    singleOf(::PaymentHistoryDataSourceImpl) { bind<PaymentHistoryDataSource>() }
    singleOf(::GasStationDataSourceImpl) { bind<GasStationDataSource>() }

    singleOf(::ThemeRepositoryImpl) { bind<ThemeRepository>() }
    singleOf(::AuthenticationRepositoryImpl) { bind<AuthenticationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::LoyaltyCardRepositoryImpl) { bind<LoyaltyCardRepository>() }
    singleOf(::PaymentHistoryRepositoryImpl) { bind<PaymentHistoryRepository>() }
    singleOf(::GasStationRepositoryImpl) { bind<GasStationRepository>() }
}