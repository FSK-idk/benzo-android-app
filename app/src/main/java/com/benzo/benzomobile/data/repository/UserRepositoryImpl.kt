package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.user.UserDataSource
import com.benzo.benzomobile.domain.model.UserUpdateData
import com.benzo.benzomobile.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUser() =
        userDataSource.getUser()

    override suspend fun fetchUser() =
        userDataSource.fetchUser()

    override suspend fun updateUser(userUpdateData: UserUpdateData) =
        userDataSource.updateUser(userUpdateData)
}