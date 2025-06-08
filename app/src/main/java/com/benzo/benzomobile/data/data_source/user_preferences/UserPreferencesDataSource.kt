package com.benzo.benzomobile.data.data_source.user_preferences

import com.benzo.benzomobile.domain.model.ThemeOption
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    val userPreferencesData: Flow<UserPreferencesData>

    suspend fun setToken(token: String?)
    suspend fun setTheme(theme: ThemeOption)
}