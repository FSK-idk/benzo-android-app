package com.benzo.benzomobile.presentation.screen.service

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.testScreen(
) {
    composable<Destination.AppGraph.GasStationsGraph.TestScreen> {
        val client: WebSocketClient = WebSocketClient("01")

        TestScreen(
            onStartClick = client::start,
            onCancelRefuelingClick = client::sendCancelRefueling,
            onSendClick = client::send,
            onStopClick = client::stop,
        )
    }
}