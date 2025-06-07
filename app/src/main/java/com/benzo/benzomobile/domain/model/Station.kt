package com.benzo.benzomobile.domain.model

data class Station(
    val id: Int,
    val status: StationStatus,
    val fuels: List<Fuel>,
)
