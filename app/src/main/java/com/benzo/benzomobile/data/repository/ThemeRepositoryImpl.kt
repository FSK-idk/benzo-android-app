package com.benzo.benzomobile.data.repository

import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : ThemeRepository {
    override fun getTheme() =
        userPreferencesDataSource.userPreferences.map { it.theme }

    override suspend fun setTheme(theme: ThemeOption) =
        userPreferencesDataSource.setTheme(theme = theme)
}