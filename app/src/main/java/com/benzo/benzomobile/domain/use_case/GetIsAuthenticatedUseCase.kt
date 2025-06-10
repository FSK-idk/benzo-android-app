package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.data.repository.AuthenticationRepositoryImpl

class GetIsAuthenticatedUseCase(
    private val authenticationRepositoryImpl: AuthenticationRepositoryImpl,
) {
    operator fun invoke() =
        authenticationRepositoryImpl.getIsAuthenticated()
}