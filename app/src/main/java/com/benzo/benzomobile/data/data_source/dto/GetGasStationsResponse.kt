package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGasStationsResponse(
    @SerialName("gas_stations")
    val gasStations: List<GasStation>
) {
    @Serializable
    data class GasStation(

        @SerialName("id")
        val id: Int,

        @SerialName("address")
        val address: String,
    )
}
