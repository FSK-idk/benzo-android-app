package com.benzo.benzomobile.data.data_source.user_preferences

import com.benzo.benzomobile.domain.model.ThemeOption
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferencesData(
    val token: String?,
    val theme: ThemeOption,
)