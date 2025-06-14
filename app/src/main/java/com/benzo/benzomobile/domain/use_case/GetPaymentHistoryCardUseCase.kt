package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.PaymentHistoryRepository

class GetPaymentHistoryCardUseCase(
    private val paymentHistoryRepository: PaymentHistoryRepository
) {
    suspend operator fun invoke() =
        paymentHistoryRepository.getPaymentHistory()
}