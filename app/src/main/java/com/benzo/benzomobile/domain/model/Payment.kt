package com.benzo.benzomobile.domain.model

import kotlinx.datetime.Instant

data class Payment(
    val dateTime: Instant,
    val gasStation: GasStation,
    val stationId: Int,
    val fuelType: FuelType,
    val fuelAmount: Int,
    val carNumber: String,
    val paymentAmount: Int,
    val bonusesUsed: Int,
    val paymentKey: String,
)
