package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.AuthenticationRepository

class RegisterUseCase(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(login: String, password: String) =
        authenticationRepository.register(
            login = login,
            password = password,
        )
}