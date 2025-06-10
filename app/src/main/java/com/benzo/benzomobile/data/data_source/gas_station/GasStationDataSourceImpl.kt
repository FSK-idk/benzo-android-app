package com.benzo.benzomobile.data.data_source.gas_station

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.LocalDateTime
import java.time.ZonedDateTime

class GasStationDataSourceImpl(
    val benzoApi: BenzoApi,
) : GasStationDataSource {
    private val _gasStations =
        MutableStateFlow<Resource<List<GasStation>>>(Resource.Loading())

    override fun getGasStations(): Flow<Resource<List<GasStation>>> =
        _gasStations

    override suspend fun fetchGasStations() {
        val response = try {
            benzoApi.retrofitService.getGasStations()
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

        _gasStations.value = Resource.Loaded(
            data = body.map {
                GasStation(
                    id = it.id,
                    address = it.address,
                )
            }
        )
    }
}