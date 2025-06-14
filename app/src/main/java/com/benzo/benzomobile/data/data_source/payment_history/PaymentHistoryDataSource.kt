package com.benzo.benzomobile.data.data_source.payment_history

import com.benzo.benzomobile.domain.model.Payment

interface PaymentHistoryDataSource {
    suspend fun getPaymentHistory(): List<Payment>
}