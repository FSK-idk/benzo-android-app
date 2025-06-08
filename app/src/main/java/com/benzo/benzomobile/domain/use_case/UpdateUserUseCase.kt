package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.UserUpdate
import com.benzo.benzomobile.domain.repository.UserRepository

class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userUpdate: UserUpdate) =
        userRepository.updateUser(userUpdate = userUpdate)
}
