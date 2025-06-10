package com.benzo.benzomobile.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun getIsAuthenticated(): Flow<Boolean>

    suspend fun register(login: String, password: String)
    suspend fun login(login: String, password: String)
    suspend fun logout()
}