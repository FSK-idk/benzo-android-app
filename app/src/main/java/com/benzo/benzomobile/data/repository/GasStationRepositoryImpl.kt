package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.gas_station.GasStationDataSource
import com.benzo.benzomobile.domain.repository.GasStationRepository

class GasStationRepositoryImpl(
    private val gasStationDataSource: GasStationDataSource,
) : GasStationRepository {
    override fun getGasStations() =
        gasStationDataSource.getGasStations()

    override suspend fun fetchGasStations() =
        gasStationDataSource.fetchGasStations()

    override suspend fun getGasStationStations(gasStationId: Int) =
        gasStationDataSource.getGasStationStations(gasStationId = gasStationId)
}