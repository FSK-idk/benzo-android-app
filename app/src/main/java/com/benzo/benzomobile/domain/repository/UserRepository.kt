package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdate
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<Result<User>>

    suspend fun fetchUser()
    suspend fun updateUser(userUpdate: UserUpdate): Result<Unit>
}