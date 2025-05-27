package com.benzo.benzomobile.presentation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination

@Composable
fun GraphRoot(
    modifier: Modifier = Modifier,
) {
    val rootNavController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = Destination.LoginGraphRoot,
    ) {
        loginGraphRoot(
            onNavigateToAppGraphRoot = {
                rootNavController.navigate(Destination.AppGraphRoot) {
                    popUpTo(Destination.LoginGraphRoot) {
                        inclusive = true
                    }
                }
            }
        )

        appGraphRoot()
    }
}