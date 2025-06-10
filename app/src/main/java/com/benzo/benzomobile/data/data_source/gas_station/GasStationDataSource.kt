package com.benzo.benzomobile.data.data_source.gas_station

import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface GasStationDataSource {
    fun getGasStations(): Flow<Resource<List<GasStation>>>

    suspend fun fetchGasStations()
}