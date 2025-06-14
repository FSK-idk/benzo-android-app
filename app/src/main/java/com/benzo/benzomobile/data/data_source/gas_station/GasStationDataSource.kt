package com.benzo.benzomobile.data.data_source.gas_station

import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station

interface GasStationDataSource {
    suspend fun getGasStations(filterQuery: FilterQuery): List<GasStation>
    suspend fun getGasStationStations(gasStationId: Int): List<Station>
    suspend fun getStationFuels(stationId: Int): List<Fuel>
}