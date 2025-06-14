package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.domain.repository.GasStationRepository

class GetGasStationsUseCase(
    private val gasStationRepository: GasStationRepository,
) {
    suspend operator fun invoke(filterQuery: FilterQuery) =
        gasStationRepository.getGasStations(filterQuery = filterQuery)
}