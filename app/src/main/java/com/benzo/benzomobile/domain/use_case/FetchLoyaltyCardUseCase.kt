package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository

class FetchLoyaltyCardUseCase(
    private val loyaltyCardRepository: LoyaltyCardRepository,
) {
    suspend operator fun invoke() =
        loyaltyCardRepository.fetchLoyaltyCard()
}