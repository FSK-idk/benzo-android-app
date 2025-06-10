package com.benzo.benzomobile.data.data_source.authentication

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource

class AuthenticationDataSourceImpl(
    private val userPreferencesDataSource: UserPreferencesDataSource,
    private val benzoApi: BenzoApi,
) : AuthenticationDataSource {
    override suspend fun register(login: String, password: String){
        val response = try {
            benzoApi.retrofitService.registerUser(
                login = login,
                password = password,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val body = response.body()

        if (body == null) {
            Log.e(TAG, "Body is empty")
            throw Exception("Ошибка сети")
        }

        userPreferencesDataSource.setToken("Token ${body.token}")
    }

    override suspend fun login(login: String, password: String) {
        val response = try {
            benzoApi.retrofitService.loginUser(
                login = login,
                password = password,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val body = response.body()

        if (body == null) {
            Log.e(TAG, "Body is empty")
            throw Exception("Ошибка сети")
        }

        userPreferencesDataSource.setToken("Token ${body.token}")
    }

    override suspend fun logout() {
        userPreferencesDataSource.setToken(null)
    }
}