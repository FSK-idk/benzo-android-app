package com.benzo.benzomobile.data.data_source.user

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class UserDataSourceImpl(
    val benzoApi: BenzoApi,
    val userPreferencesDataSource: UserPreferencesDataSource,
) : UserDataSource {
    private val _user = MutableStateFlow<Resource<User>>(Resource.Loading())

    override fun getUser(): Flow<Resource<User>> = _user

    override suspend fun fetchUser() {
        val token = userPreferencesDataSource.userPreferences.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            throw Exception("Ошибка сети")
        }

        val response = try {
            benzoApi.retrofitService.getUser(
                token = token
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val body = response.body()!!

        _user.value = Resource.Loaded(
            data = User(
                login = body.login,
                name = body.name,
                birthDate = body.birthDate,
                carNumber = body.carNumber,
                phoneNumber = body.phoneNumber,
                email = body.email,
                gender = when (body.gender) {
                    "M" -> GenderOption.MALE
                    "F" -> GenderOption.FEMALE
                    else -> GenderOption.NONE
                },
                penalty = body.penalty,
            )
        )
    }

    override suspend fun updateUser(userUpdateData: UserUpdateData) {
        val token = userPreferencesDataSource.userPreferences.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            throw Exception("Ошибка сети")
        }

        val response = try {
            benzoApi.retrofitService.updateUser(
                token = token,
                name = userUpdateData.name,
                birthDate = userUpdateData.birthDate,
                carNumber = userUpdateData.carNumber,
                phoneNumber = userUpdateData.phoneNumber,
                email = userUpdateData.email,
                gender = when (userUpdateData.gender) {
                    GenderOption.MALE -> "M"
                    GenderOption.FEMALE -> "F"
                    GenderOption.NONE -> {
                        Log.e(TAG, "Gender is NONE")
                        throw Exception("Ошибка сети")
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }
    }
}