    package com.benzo.benzomobile.nav

    import kotlinx.serialization.Serializable

    sealed interface Destination {
        val route: String
            get() = this::class.simpleName ?: error("Unnamed destination")
        @Serializable
        data object ExampleScreen: Destination

        @Serializable
        data object ExampleSecondScreen: Destination

        @kotlinx.serialization.Serializable
        data object ProfileScreen : Destination
        object ThemeScreen : Destination {
            override val route: String = "theme_screen"
        }

    }