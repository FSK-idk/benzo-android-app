package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSource
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository

class LoyaltyCardRepositoryImpl(
    private val loyaltyCardDataSource: LoyaltyCardDataSource,
) : LoyaltyCardRepository {
    override suspend fun getLoyaltyCard() =
        loyaltyCardDataSource.getLoyaltyCard()
}