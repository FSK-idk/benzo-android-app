package com.benzo.benzomobile.presentation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination

@Composable
fun GraphRoot(
    isAuthenticated: Boolean,
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = getDestination(isAuthenticated),
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

        appGraphRoot(
            onNavigateToLoginGraphRoot = {
                rootNavController.navigate(Destination.LoginGraphRoot) {
                    popUpTo(Destination.AppGraphRoot) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

private fun getDestination(isAuthenticated: Boolean) : Destination =
    when (isAuthenticated) {
        true -> Destination.AppGraphRoot
        false -> Destination.LoginGraphRoot
    }