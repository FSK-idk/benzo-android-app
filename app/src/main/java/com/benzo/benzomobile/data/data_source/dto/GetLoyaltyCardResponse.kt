package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLoyaltyCardResponse(
    @SerialName("number")
    val number: String,

    @SerialName("balance")
    val balance: Int,
)
