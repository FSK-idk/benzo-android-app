package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGasStationStationsResponse(
    @SerialName("stations")
    val stations: List<Station>,
) {
    @Serializable
    data class Station(
        @SerialName("id")
        val id: Int,

        @SerialName("status")
        val status: String,
    )
}
