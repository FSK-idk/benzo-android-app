package com.benzo.benzomobile.domain.use_case

import com.benzo.benzomobile.domain.repository.ThemeRepository

class GetThemeUseCase(
    private val themeRepository: ThemeRepository,
) {
    operator fun invoke() =
        themeRepository.theme
}