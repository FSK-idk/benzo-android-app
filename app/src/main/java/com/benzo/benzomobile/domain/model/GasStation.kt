package com.benzo.benzomobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GasStation(
    val id: Int,
    val address: String,
)
