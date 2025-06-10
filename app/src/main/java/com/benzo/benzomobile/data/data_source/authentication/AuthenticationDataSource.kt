package com.benzo.benzomobile.data.data_source.authentication

interface AuthenticationDataSource {
    suspend fun register(login: String, password: String)
    suspend fun login(login: String, password: String)
    suspend fun logout()
}