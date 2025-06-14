package com.benzo.benzomobile.domain.model

enum class ThemeOption {
    SYSTEM,
    LIGHT,
    DARK,
}

fun getThemeName(theme: ThemeOption) =
    when (theme) {
        ThemeOption.SYSTEM -> "Системная"
        ThemeOption.LIGHT -> "Светлая"
        ThemeOption.DARK -> "Тёмная"
    }