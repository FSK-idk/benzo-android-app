package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface PaymentHistoryRepository {
    fun getPaymentHistory(): Flow<Resource<List<Payment>>>

    suspend fun fetchPaymentHistory()
}