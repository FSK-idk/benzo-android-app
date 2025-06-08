package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.repository.ThemeRepository

class SetThemeUseCase(
    private val themeRepository: ThemeRepository,
) {
    suspend operator fun invoke(theme: ThemeOption) =
        themeRepository.setTheme(theme = theme)
}