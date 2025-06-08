package com.benzo.benzomobile.data.data_source.user

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.authentication.AuthenticationDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class UserDataSourceImpl(
    val benzoApi: BenzoApi,
    val authenticationDataSource: AuthenticationDataSource,
) : UserDataSource {
    private val _userData = MutableStateFlow<Result<UserData>>(Result.Loading())

    override fun getUserData(): Flow<Result<UserData>> = _userData

    override suspend fun fetchUserData() {
        val token = authenticationDataSource.authenticationData.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            _userData.value = Result.Error(message = "Error loading user data")
            return
        }

        val getUserResponse = try {
            benzoApi.retrofitService.getUser(
                token = token
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            _userData.value = Result.Error(message = "Error loading user data")
            return
        }

        if (!getUserResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            _userData.value = Result.Error(message = "Error loading user data")
            return
        }

        val userDto = getUserResponse.body()!!

        _userData.value = Result.Success(
            data = UserData(
                login = userDto.login,
                name = userDto.name,
                birthDate = userDto.birthDate,
                carNumber = userDto.carNumber,
                penalty = userDto.penalty,
            )
        )
    }

    override suspend fun updateUserData(userDataUpdate: UserDataUpdate): Result<Unit> {
        val token = authenticationDataSource.authenticationData.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            return Result.Error(message = "Error loading user data")
        }

        val updateUserResponse = try {
            benzoApi.retrofitService.updateUser(
                token = token,
                name = userDataUpdate.name,
                birthDate = userDataUpdate.birthDate,
                carNumber = userDataUpdate.carNumber,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            return Result.Error(message = "Error loading user data")
        }

        if (!updateUserResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            return Result.Error(message = "Error loading user data")
        }

        return Result.Success(data = Unit)
    }
}