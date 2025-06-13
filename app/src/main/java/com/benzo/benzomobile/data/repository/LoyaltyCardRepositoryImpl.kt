package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSource
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import kotlinx.coroutines.flow.map

class LoyaltyCardRepositoryImpl(
    private val loyaltyCardDataSource: LoyaltyCardDataSource,
) : LoyaltyCardRepository {
    override suspend fun getLoyaltyCard() =
        loyaltyCardDataSource.getLoyaltyCard()
}