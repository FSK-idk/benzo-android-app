package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.gas_station.GasStationDataSource
import com.benzo.benzomobile.data.data_source.loyalty_card.LoyaltyCardDataSource
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.repository.GasStationRepository
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import kotlinx.coroutines.flow.map

class GasStationRepositoryImpl(
    private val gasStationDataSource: GasStationDataSource,
) : GasStationRepository {
    override fun getGasStations() =
        gasStationDataSource.getGasStations()

    override suspend fun fetchGasStations() =
        gasStationDataSource.fetchGasStations()
}