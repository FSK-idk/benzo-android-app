package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.GasStationRepository
import com.benzo.benzomobile.domain.repository.LoyaltyCardRepository
import com.benzo.benzomobile.domain.repository.UserRepository

class GetGasStationsUseCase(
    private val gasStationRepository: GasStationRepository,
) {
    operator fun invoke() =
        gasStationRepository.getGasStations()
}