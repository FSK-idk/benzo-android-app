package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.bank.BankDataSource
import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.repository.BankRepository

class BankRepositoryImpl(
    private val bankDataSource: BankDataSource,
) : BankRepository {
    override suspend fun pay(payRequest: PayRequest) =
        bankDataSource.pay(payRequest = payRequest)
}