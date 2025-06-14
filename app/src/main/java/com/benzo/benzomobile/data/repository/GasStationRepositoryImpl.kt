package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.gas_station.GasStationDataSource
import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.repository.GasStationRepository

class GasStationRepositoryImpl(
    private val gasStationDataSource: GasStationDataSource,
) : GasStationRepository {
    override suspend fun getGasStations(filterQuery: FilterQuery) =
        gasStationDataSource.getGasStations(filterQuery = filterQuery)

    override suspend fun getGasStationStations(gasStationId: Int) =
        gasStationDataSource.getGasStationStations(gasStationId = gasStationId)

    override suspend fun getStationFuels(stationId: Int): List<Fuel> =
        gasStationDataSource.getStationFuels(stationId)
}