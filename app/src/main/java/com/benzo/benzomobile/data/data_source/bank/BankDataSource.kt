package com.benzo.benzomobile.data.data_source.bank

import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.model.PayResponse

interface BankDataSource {
    suspend fun pay(payRequest: PayRequest): PayResponse
}