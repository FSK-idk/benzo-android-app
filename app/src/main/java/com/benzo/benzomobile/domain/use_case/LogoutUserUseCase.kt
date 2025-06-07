package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.AuthenticationRepository

class LogoutUserUseCase(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke() =
        authenticationRepository.logout()
}