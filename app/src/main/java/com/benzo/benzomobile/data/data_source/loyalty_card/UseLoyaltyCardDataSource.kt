package com.benzo.benzomobile.data.data_source.loyalty_card

import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardDataSource {
    fun getLoyaltyCard(): Flow<Resource<LoyaltyCard>>

    suspend fun fetchLoyaltyCard()
}