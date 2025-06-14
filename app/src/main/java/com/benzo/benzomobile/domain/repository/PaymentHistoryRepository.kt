package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.Payment

interface PaymentHistoryRepository {
    suspend fun getPaymentHistory(): List<Payment>
}