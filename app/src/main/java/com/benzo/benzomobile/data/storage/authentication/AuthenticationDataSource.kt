package com.benzo.benzomobile.data.storage.authentication

import kotlinx.coroutines.flow.Flow

interface AuthenticationDataSource {
    val authenticationData: Flow<AuthenticationData>

    suspend fun register(login: String, password: String)
    suspend fun login(login: String, password: String)
    suspend fun logout()
}