package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.payment_history.PaymentHistoryDataSource
import com.benzo.benzomobile.domain.repository.PaymentHistoryRepository

class PaymentHistoryRepositoryImpl(
    private val paymentHistoryDataSource: PaymentHistoryDataSource,
) : PaymentHistoryRepository {
    override fun getPaymentHistory() =
        paymentHistoryDataSource.getPaymentHistory()

    override suspend fun fetchPaymentHistory() =
        paymentHistoryDataSource.fetchPaymentHistory()
}