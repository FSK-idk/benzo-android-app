package com.benzo.benzomobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val token: String?,
    val themeOption: ThemeOption,
)