package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSource
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import kotlinx.coroutines.flow.map

class LoyaltyCardRepositoryImpl(
    private val loyaltyCardDataSource: LoyaltyCardDataSource,
) : LoyaltyCardRepository {
    override fun getLoyaltyCard() =
        loyaltyCardDataSource.getLoyaltyCardData().map {
            when (it) {
                is Result.Loading -> Result.Loading()

                is Result.Success -> Result.Success(
                    data = LoyaltyCard(
                        number = it.data.number,
                        balance = it.data.balance,
                    )
                )

                is Result.Error -> Result.Error(message = it.message)
            }
        }

    override suspend fun fetchLoyaltyCard() =
        loyaltyCardDataSource.fetchLoyaltyCardData()
}