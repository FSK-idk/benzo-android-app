package com.benzo.benzomobile.presentation.screen.stations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.stationsScreen() {
    composable<Destination.AppGraph.StationsGraph.StationsScreen> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("under maintenance")
        }
    }
}