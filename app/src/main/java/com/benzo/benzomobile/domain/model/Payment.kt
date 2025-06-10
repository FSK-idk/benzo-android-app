package com.benzo.benzomobile.domain.model

import java.time.ZonedDateTime

data class Payment(
    val dateTime: ZonedDateTime,
    val gasStation: GasStation,
    val stationId: Int,
    val fuelType: FuelType,
    val fuelAmount: Int,
    val carNumber: String,
    val paymentAmount: Int,
    val bonusesUsed: Int,
    val paymentKey: String,
)
