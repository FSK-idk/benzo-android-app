package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.AuthenticationRepository

class LoginUserUseCase(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(login: String, password: String) =
        authenticationRepository.login(
            login = login,
            password = password,
        )
}