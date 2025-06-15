package com.benzo.benzomobile.data.data_source.payment_history

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Instant

class PaymentHistoryDataSourceImpl(
    val benzoApi: BenzoApi,
    val userPreferencesDataSource: UserPreferencesDataSource,
) : PaymentHistoryDataSource {
    override suspend fun getPaymentHistory(): List<Payment> {
        val token = userPreferencesDataSource.userPreferences.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            throw Exception("Ошибка сети")
        }

        val response = try {
            benzoApi.retrofitService.getPaymentHistory(
                token = token
            )
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

        return body.history.map {
            Payment(
                dateTime = Instant.parse(it.dateTime),
                gasStation = GasStation(
                    id = it.gasStationId,
                    address = it.gasStationAddress,
                ),
                stationId = it.stationId,
                fuelType = when (it.fuelType) {
                    "92" -> FuelType.PETROL_92
                    "95" -> FuelType.PETROL_95
                    "98" -> FuelType.PETROL_98
                    "DT" -> FuelType.DIESEL
                    else -> {
                        Log.e(TAG, "Fuel type is wrong")
                        throw Exception("Ошибка сети")
                    }
                },
                fuelAmount = it.fuelAmount,
                carNumber = it.carNumber,
                paymentAmount = it.paymentAmount,
                bonusesUsed = it.bonusesUsed,
                paymentKey = it.paymentKey,
            )
        }
    }
}