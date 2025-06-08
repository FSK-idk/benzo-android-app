package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.user.UserDataSource
import com.benzo.benzomobile.data.data_source.user.UserDataUpdate
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.model.UserUpdate
import com.benzo.benzomobile.domain.repository.UserRepository
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUser() =
        userDataSource.getUserData().map {
            when (it) {
                is Result.Loading -> Result.Loading()

                is Result.Success -> Result.Success(
                    data = User(
                        login = it.data.login,
                        name = it.data.name,
                        birthDate = it.data.birthDate,
                        carNumber = it.data.carNumber,
                        penalty = it.data.penalty,
                    )
                )

                is Result.Error -> Result.Error(message = it.message)
            }
        }

    override suspend fun fetchUser() =
        userDataSource.fetchUserData()

    override suspend fun updateUser(userUpdate: UserUpdate) =
        userDataSource.updateUserData(
            UserDataUpdate(
                name = userUpdate.name,
                birthDate = userUpdate.birthDate,
                carNumber = userUpdate.carNumber,
            )
        )
}