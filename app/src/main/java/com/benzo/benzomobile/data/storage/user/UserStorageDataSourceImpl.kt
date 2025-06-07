package com.benzo.benzomobile.data.storage.user

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.storage.authentication.AuthenticationDataSource
import com.benzo.benzomobile.data.storage.dto.UserDto
import kotlinx.coroutines.flow.first


class UserStorageDataSourceImpl(
    val benzoApi: BenzoApi,
    val authenticationDataSource: AuthenticationDataSource,
) : UserStorageDataSource {
    override suspend fun fetch(): UserDto {
        val token = authenticationDataSource.authenticationData.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            throw Exception("Internal Error")
        }

        val getUserResponse = try {
            benzoApi.retrofitService.getUser(
                token = token
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getUserResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val userDto = getUserResponse.body()!!

        return userDto
    }
}