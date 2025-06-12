package com.benzo.benzomobile.data.data_source.gas_station

import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface GasStationDataSource {
    fun getGasStations(): Flow<Resource<List<GasStation>>>

    suspend fun fetchGasStations()

    suspend fun getGasStationStations(gasStationId: Int): List<Station>

    suspend fun getStationFuels(stationId: Int): List<Fuel>
}