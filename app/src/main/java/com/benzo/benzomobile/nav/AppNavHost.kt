package com.benzo.benzomobile.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val rootNavController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = Destination.LoginRoot,
    ) {
        loginRoot(
            onNavigateToAppRoot = {
                rootNavController.navigate(Destination.AppRoot) {
                    popUpTo(Destination.LoginRoot) {
                        inclusive = true
                    }
                }
            }
        )

        appRoot()
    }
}