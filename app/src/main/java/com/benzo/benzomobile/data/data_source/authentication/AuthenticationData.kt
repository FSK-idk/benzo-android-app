package com.benzo.benzomobile.data.data_source.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationData(
    val token: String?,
)