package com.benzo.benzomobile.data.data_source.gas_station

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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

    override suspend fun getGasStationStations(gasStationId: Int): List<Station> {
        val responseStations = try {
            benzoApi.retrofitService.getGasStationStations(
                gasStationId = gasStationId
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!responseStations.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val bodyStations = responseStations.body()

        if (bodyStations == null) {
            Log.e(TAG, "Body is empty")
            throw Exception("Ошибка сети")
        }

        return bodyStations.stations.map { station ->
            val responseFuels = try {
                benzoApi.retrofitService.getStationFuels(
                    stationId = station.id
                )
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                throw Exception("Ошибка сети")
            }

            if (!responseFuels.isSuccessful) {
                Log.e(TAG, "Response is not successful")
                throw Exception("Ошибка сети")
            }

            val bodyFuels = responseFuels.body()

            if (bodyFuels == null) {
                Log.e(TAG, "Body is empty")
                throw Exception("Ошибка сети")
            }

            Station(
                id = station.id,
                status = when (station.status) {
                    "busy_offline" -> StationStatus.BUSY_OFFLINE
                    "busy_online" -> StationStatus.BUSY_ONLINE
                    "not_working" -> StationStatus.NOT_WORKING
                    "free" -> StationStatus.FREE
                    else -> {
                        Log.e(TAG, "Station status is wrong")
                        throw Exception("Ошибка сети")
                    }
                },
                fuels = bodyFuels.fuels.map { fuel ->
                    Fuel(
                        type = when (fuel.fuelType) {
                            "92" -> FuelType.PETROL_92
                            "95" -> FuelType.PETROL_95
                            "98" -> FuelType.PETROL_98
                            "DT" -> FuelType.DIESEL
                            else -> {
                                Log.e(TAG, "Fuel type is wrong")
                                throw Exception("Ошибка сети")
                            }
                        },
                        price = fuel.price,
                    )
                },
            )
        }
    }
}