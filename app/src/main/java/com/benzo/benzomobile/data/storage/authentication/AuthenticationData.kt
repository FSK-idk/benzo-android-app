package com.benzo.benzomobile.data.storage.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationData(
    val token: String?,
)