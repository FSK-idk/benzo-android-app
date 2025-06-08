package com.benzo.benzomobile.data.storage.user_preferences

import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    val userPreferences: Flow<UserPreferences>

    suspend fun setToken(token: String?)
    suspend fun setTheme(theme: ThemeOption)
}