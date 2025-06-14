package com.benzo.benzomobile.domain.model

enum class FuelType(
    value: String
) {
    PETROL_92("92"),
    PETROL_95("95"),
    PETROL_98("98"),
    DIESEL("DT"),
}

fun getFuelTypeName(fuelType: FuelType) =
    when (fuelType) {
        FuelType.PETROL_92 -> "АИ-92"
        FuelType.PETROL_95 -> "АИ-95"
        FuelType.PETROL_98 -> "АИ-98"
        FuelType.DIESEL -> "ДТ"
    }