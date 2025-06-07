package com.benzo.benzomobile.presentation.screen.stations

import com.benzo.benzomobile.domain.model.GasStation

data class StationsScreenUiState(
    val gasStations: List<GasStation> = listOf(),
)