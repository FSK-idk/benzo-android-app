package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardRepository {
    fun getLoyaltyCard(): Flow<Result<LoyaltyCard>>

    suspend fun fetchLoyaltyCard()
}