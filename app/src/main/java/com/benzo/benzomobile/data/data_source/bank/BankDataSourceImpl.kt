package com.benzo.benzomobile.data.data_source.bank

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.data.service.bank_api.BankApi
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.model.PayResponse
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BankDataSourceImpl(
    val bankApi: BankApi,
) : BankDataSource {
    override suspend fun pay(payRequest: PayRequest): PayResponse {
        val response = try {
            bankApi.retrofitService.pay(payRequest)
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val body = response.body()

        if (body == null) {
            Log.e(TAG, "Body is empty")
            throw Exception("Ошибка сети")
        }

        return body
    }
}