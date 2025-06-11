package com.benzo.benzomobile.presentation

import com.benzo.benzomobile.domain.model.GasStation
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object LoginGraphRoot : Destination

    sealed interface LoginGraph {
        @Serializable
        data object WelcomeScreen : LoginGraph

        @Serializable
        data object RegisterScreen : LoginGraph

        @Serializable
        data object LoginScreen : LoginGraph
    }

    @Serializable
    data object AppGraphRoot : Destination

    sealed interface AppGraph {
        @Serializable
        data object ProfileGraphRoot : AppGraph

        sealed interface ProfileGraph {
            @Serializable
            data object ProfileScreen : ProfileGraph

            @Serializable
            data object SettingsScreen : ProfileGraph

            @Serializable
            data object EditProfileScreen : ProfileGraph

            @Serializable
            data object PaymentHistoryScreen : ProfileGraph
        }

        @Serializable
        data object GasStationsGraphRoot : AppGraph

        sealed interface GasStationsGraph {
            @Serializable
            data object GasStationsScreen : GasStationsGraph

            @Serializable
            data class GasStationStationsScreen(val gasStationId: Int, val gasStationAddress: String) : GasStationsGraph
        }

        @Serializable
        data object LoyaltyCardGraphRoot : AppGraph

        sealed interface LoyaltyCardGraph {
            @Serializable
            data object LoyaltyCardScreen : LoyaltyCardGraph
        }
    }
}