package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGasStationsResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("address")
    val address: String,
)
