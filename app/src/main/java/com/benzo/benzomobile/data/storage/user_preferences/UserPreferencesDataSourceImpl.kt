package com.benzo.benzomobile.data.storage.user_preferences

import androidx.datastore.core.DataStore
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

class UserPreferencesDataSourceImpl(
    private val dataStore: DataStore<UserPreferences>,
) : UserPreferencesDataSource {
    override val userPreferences: Flow<UserPreferences> =
        dataStore.data

    override suspend fun setToken(token: String?) {
        dataStore.updateData { it.copy(token = token) }
    }

    override suspend fun setThemeOption(themeOption: ThemeOption) {
        dataStore.updateData { it.copy(themeOption = themeOption) }
    }
}