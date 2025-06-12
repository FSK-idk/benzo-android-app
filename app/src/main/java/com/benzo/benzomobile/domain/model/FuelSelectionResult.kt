package com.benzo.benzomobile.domain.model

data class FuelSelectionResult(
    val fuelType: FuelType,
    val fuelAmount: Int,
    val paymentAmount: Int,
)
