package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.model.PayResponse

interface BankRepository {
    suspend fun pay(payRequest: PayRequest): PayResponse
}