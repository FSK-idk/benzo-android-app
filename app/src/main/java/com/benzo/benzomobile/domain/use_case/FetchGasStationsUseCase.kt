package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.GasStationRepository

class FetchGasStationsUseCase(
    private val gasStationRepository: GasStationRepository,
) {
    suspend operator fun invoke() =
        gasStationRepository.fetchGasStations()
}