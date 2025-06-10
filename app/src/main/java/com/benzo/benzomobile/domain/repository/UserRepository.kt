package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdateData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<Resource<User>>

    suspend fun fetchUser()
    suspend fun updateUser(userUpdateData: UserUpdateData)
}