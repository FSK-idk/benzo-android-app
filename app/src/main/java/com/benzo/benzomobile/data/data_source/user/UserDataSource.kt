package com.benzo.benzomobile.data.data_source.user

import com.benzo.benzomobile.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getUserData(): Flow<Result<UserData>>

    suspend fun fetchUserData()
    suspend fun updateUserData(userDataUpdate: UserDataUpdate): Result<Unit>
}