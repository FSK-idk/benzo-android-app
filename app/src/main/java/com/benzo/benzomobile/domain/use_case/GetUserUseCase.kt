package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() =
        userRepository.getUser()
}