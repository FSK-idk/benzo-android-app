package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.repository.BankRepository

class PayUseCase(
    private val bankRepository: BankRepository,
) {
    suspend operator fun invoke(payRequest: PayRequest) =
        bankRepository.pay(payRequest = payRequest)
}