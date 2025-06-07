package com.benzo.benzomobile.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.benzo.benzomobile.data.repository.AuthenticationRepositoryImpl
import com.benzo.benzomobile.data.repository.ThemeRepositoryImpl
import com.benzo.benzomobile.data.repository.UserRepositoryImpl
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.storage.user.UserStorageDataSource
import com.benzo.benzomobile.data.storage.user.UserStorageDataSourceImpl
import com.benzo.benzomobile.data.storage.authentication.AuthenticationDataSource
import com.benzo.benzomobile.data.storage.authentication.AuthenticationDataSourceImpl
import com.benzo.benzomobile.domain.model.UserPreferences
import com.benzo.benzomobile.data.storage.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.data.storage.user_preferences.UserPreferencesDataSourceImpl
import com.benzo.benzomobile.data.storage.user_preferences.UserPreferencesSerializer
import com.benzo.benzomobile.domain.repository.AuthenticationRepository
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
    singleOf(::UserStorageDataSourceImpl) { bind<UserStorageDataSource>() }

    singleOf(::ThemeRepositoryImpl) { bind<ThemeRepository>() }
    singleOf(::AuthenticationRepositoryImpl) { bind<AuthenticationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}