package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.LoyaltyCard

interface LoyaltyCardRepository {
    suspend fun getLoyaltyCard(): LoyaltyCard
}