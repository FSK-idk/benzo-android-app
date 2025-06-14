package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.GasStationRepository

class GetGasStationStationsUseCase(
    private val gasStationRepository: GasStationRepository,
) {
    suspend operator fun invoke(gasStationId: Int) =
        gasStationRepository.getGasStationStations(gasStationId = gasStationId)
}