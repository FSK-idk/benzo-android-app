package com.benzo.benzomobile.domain.repository

import com.benzo.benzomobile.domain.model.ThemeOption
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getTheme(): Flow<ThemeOption>

    suspend fun setTheme(theme: ThemeOption)
}