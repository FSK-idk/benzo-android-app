package com.benzo.benzomobile.data.data_source.payment_history

import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface PaymentHistoryDataSource {
    fun getPaymentHistory(): Flow<Resource<List<Payment>>>

    suspend fun fetchPaymentHistory()
}