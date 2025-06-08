package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import com.benzo.benzomobile.domain.repository.UserRepository

class GetLoyaltyCardUseCase(
    private val loyaltyCardRepository: LoyaltyCardRepository
) {
    operator fun invoke() =
        loyaltyCardRepository.getLoyaltyCard()
}