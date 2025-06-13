package com.benzo.benzomobile.data.data_source.loyalty_card

import com.benzo.benzomobile.domain.model.LoyaltyCard

interface LoyaltyCardDataSource {
    suspend fun getLoyaltyCard(): LoyaltyCard
}