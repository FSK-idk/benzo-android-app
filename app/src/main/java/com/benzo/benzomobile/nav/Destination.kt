    package com.benzo.benzomobile.nav

    import kotlinx.serialization.Serializable

    sealed interface Destination {
        // root

        @Serializable
        data object LoginRoot: Destination

        @Serializable
        data object  AppRoot: Destination

        // sub roots

        @Serializable
        data object ProfileRoot: Destination

        @Serializable
        data object StationsRoot: Destination

        @Serializable
        data object LoyaltyCardRoot: Destination

        // login

        @Serializable
        data object WelcomeScreen: Destination

        @Serializable
        data object RegisterScreen: Destination

        @Serializable
        data object LoginScreen: Destination

        // profile

        @Serializable
        data object ProfileScreen: Destination

        @Serializable
        data object SettingsScreen: Destination

        @Serializable
        data object EditProfileScreen: Destination

        // loyalty card

        @Serializable
        data object LoyaltyCardScreen: Destination

        // loyalty card

        @Serializable
        data object StationsScreen: Destination
    }