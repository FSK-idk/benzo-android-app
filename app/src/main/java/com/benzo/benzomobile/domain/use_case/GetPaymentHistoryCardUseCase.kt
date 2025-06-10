package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import com.benzo.benzomobile.domain.repository.PaymentHistoryRepository
import com.benzo.benzomobile.domain.repository.UserRepository

class GetPaymentHistoryCardUseCase(
    private val paymentHistoryRepository: PaymentHistoryRepository
) {
    operator fun invoke() =
        paymentHistoryRepository.getPaymentHistory()
}