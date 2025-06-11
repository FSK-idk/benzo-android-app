package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStationFuelsResponse(
    @SerialName("fuels")
    val fuels: List<Fuel>,
) {
    @Serializable
    data class Fuel(
        @SerialName("fuel_type")
        val fuelType: String,

        @SerialName("price")
        val price: Int,
    )
}