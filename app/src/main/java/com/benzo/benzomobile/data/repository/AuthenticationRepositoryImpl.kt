package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.storage.authentication.AuthenticationDataSource
import com.benzo.benzomobile.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthenticationRepositoryImpl(
    private val authenticationDataSource: AuthenticationDataSource,
) : AuthenticationRepository {
    override val isAuthenticated: Flow<Boolean> =
        authenticationDataSource.authenticationData.map { it.token != null }

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