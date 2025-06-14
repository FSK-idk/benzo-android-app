package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository

class GetLoyaltyCardUseCase(
    private val loyaltyCardRepository: LoyaltyCardRepository
) {
    suspend operator fun invoke() =
        loyaltyCardRepository.getLoyaltyCard()
}