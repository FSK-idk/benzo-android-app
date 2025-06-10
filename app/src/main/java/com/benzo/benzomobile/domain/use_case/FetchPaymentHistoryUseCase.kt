package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.PaymentHistoryRepository

class FetchPaymentHistoryUseCase(
    private val paymentHistoryRepository: PaymentHistoryRepository,
) {
    suspend operator fun invoke() =
        paymentHistoryRepository.fetchPaymentHistory()
}