package com.benzo.benzomobile.data.storage.user

import com.benzo.benzomobile.data.storage.dto.UserDto

interface UserStorageDataSource {
    suspend fun fetch(): UserDto
}