package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.GasStationRepository

class GetStationFuelsUseCase(
    private val gasStationRepository: GasStationRepository,
) {
    suspend operator fun invoke(stationId: Int) =
        gasStationRepository.getStationFuels(stationId = stationId)
}