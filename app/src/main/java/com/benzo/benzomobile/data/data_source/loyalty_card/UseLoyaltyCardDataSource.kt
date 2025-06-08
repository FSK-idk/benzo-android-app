package com.benzo.benzomobile.data.data_source.loyalty_card

import com.benzo.benzomobile.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardDataSource {
    fun getLoyaltyCardData(): Flow<Result<LoyaltyCardData>>

    suspend fun fetchLoyaltyCardData()
}