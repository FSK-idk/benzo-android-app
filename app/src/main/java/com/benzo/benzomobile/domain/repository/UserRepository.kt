package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdateData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): User

    suspend fun updateUser(userUpdateData: UserUpdateData)
}