package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.ThemeRepository

class GetThemeOptionUseCase(
    private val themeRepository: ThemeRepository,
) {
    operator fun invoke() =
        themeRepository.themeOption
}