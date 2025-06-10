package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.UserUpdateData
import com.benzo.benzomobile.domain.repository.UserRepository

class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userUpdateData: UserUpdateData) =
        userRepository.updateUser(userUpdateData = userUpdateData)
}
