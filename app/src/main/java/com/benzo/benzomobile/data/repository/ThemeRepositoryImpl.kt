package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.storage.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : ThemeRepository {
    override val themeOption: Flow<ThemeOption> =
        userPreferencesDataSource.userPreferences.map { it.themeOption }

    override suspend fun setThemeOption(themeOption: ThemeOption) =
        userPreferencesDataSource.setThemeOption(themeOption = themeOption)
}