package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.User

interface UserRepository {
    suspend fun fetch(): User
}