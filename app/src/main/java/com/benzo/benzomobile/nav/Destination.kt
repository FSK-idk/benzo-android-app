package com.benzo.benzomobile.nav

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object ExampleScreen: Destination

    @Serializable
    data object ExampleSecondScreen: Destination
}