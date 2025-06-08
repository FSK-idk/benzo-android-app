package com.benzo.benzomobile.data.data_source.user_preferences

import androidx.datastore.core.DataStore
import com.benzo.benzomobile.domain.model.ThemeOption
import kotlinx.coroutines.flow.Flow

class UserPreferencesDataSourceImpl(
    private val dataStore: DataStore<UserPreferencesData>,
) : UserPreferencesDataSource {
    override val userPreferencesData: Flow<UserPreferencesData> =
        dataStore.data

    override suspend fun setToken(token: String?) {
        dataStore.updateData { it.copy(token = token) }
    }

    override suspend fun setTheme(theme: ThemeOption) {
        dataStore.updateData { it.copy(theme = theme) }
    }
}