package com.benzo.benzomobile.data.data_source.user

import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdateData
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUser(): User

    suspend fun updateUser(userUpdateData: UserUpdateData)
}