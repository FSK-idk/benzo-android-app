package com.benzo.benzomobile.data.data_source.authentication

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthenticationDataSourceImpl(
    private val userPreferences: UserPreferencesDataSource,
    private val benzoApi: BenzoApi,
) : AuthenticationDataSource {
    override val authenticationData: Flow<AuthenticationData> =
        userPreferences.userPreferencesData.map { AuthenticationData(token = it.token) }

    override suspend fun register(login: String, password: String) {
        val registerUserResponse = try {
            benzoApi.retrofitService.registerUser(
                login = login,
                password = password,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!registerUserResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val tokenDto = registerUserResponse.body()!!

        userPreferences.setToken("Token ${tokenDto.token}")
    }

    override suspend fun login(login: String, password: String) {
        val loginUserResponse = try {
            benzoApi.retrofitService.loginUser(
                login = login,
                password = password,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!loginUserResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val tokenDto = loginUserResponse.body()!!

        userPreferences.setToken("Token ${tokenDto.token}")
    }

    override suspend fun logout() {
        userPreferences.setToken(null)
    }
}