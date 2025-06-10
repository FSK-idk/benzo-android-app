package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.authentication.AuthenticationDataSource
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.map

class AuthenticationRepositoryImpl(
    private val userPreferencesDataSource: UserPreferencesDataSource,
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {
    override fun getIsAuthenticated() =
        userPreferencesDataSource.userPreferences.map { it.token != null }

    override suspend fun register(login: String, password: String) =
        authenticationDataSource.register(
            login = login,
            password = password,
        )

    override suspend fun login(login: String, password: String) =
        authenticationDataSource.login(
            login = login,
            password = password,
        )

    override suspend fun logout() =
        authenticationDataSource.logout()
}