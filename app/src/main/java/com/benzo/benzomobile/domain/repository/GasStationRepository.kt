package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface GasStationRepository {
    fun getGasStations(): Flow<Resource<List<GasStation>>>

    suspend fun fetchGasStations()
}