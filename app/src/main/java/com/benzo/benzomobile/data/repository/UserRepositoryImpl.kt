package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.storage.user.UserStorageDataSource
import com.benzo.benzomobile.data.storage.dto.toDomain
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorageDataSource: UserStorageDataSource,
) : UserRepository {
    override suspend fun fetch(): User =
        userStorageDataSource.fetch().toDomain
}