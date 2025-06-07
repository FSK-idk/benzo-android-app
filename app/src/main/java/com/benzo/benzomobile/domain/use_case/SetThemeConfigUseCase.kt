package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.repository.ThemeRepository

class SetThemeConfigUseCase(
    private val themeRepository: ThemeRepository,
) {
    suspend operator fun invoke(themeOption: ThemeOption) =
        themeRepository.setThemeOption(themeOption = themeOption)
}