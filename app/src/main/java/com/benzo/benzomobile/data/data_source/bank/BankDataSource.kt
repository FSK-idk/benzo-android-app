package com.benzo.benzomobile.data.data_source.bank

import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.model.PayResponse
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface BankDataSource {
    suspend fun pay(payRequest: PayRequest): PayResponse
}