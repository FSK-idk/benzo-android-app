package com.benzo.benzomobile.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.login.loginScreen
import com.benzo.benzomobile.presentation.screen.register.registerScreen
import com.benzo.benzomobile.presentation.screen.welcome.welcomeScreen

fun NavGraphBuilder.loginGraphRoot(
    onNavigateToAppGraphRoot: () -> Unit,
) {
    composable<Destination.LoginGraphRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.LoginGraph.WelcomeScreen,
        ) {
            welcomeScreen(
                onNavigateToRegisterScreen = {
                    navController.navigate(Destination.LoginGraph.RegisterScreen)
                },
                onNavigateToLoginScreen = {
                    navController.navigate(Destination.LoginGraph.LoginScreen)
                },
            )

            registerScreen(
                onNavigateBack = navController::navigateUp,
                onNavigateToAppGraphRoot = onNavigateToAppGraphRoot,
                onNavigateToLoginScreen = {
                    navController.navigate(Destination.LoginGraph.LoginScreen) {
                        popUpTo(Destination.LoginGraph.RegisterScreen) {
                            inclusive = true
                        }
                    }
                },
            )

            loginScreen(
                onNavigateBack = navController::navigateUp,
                onNavigateToAppGraphRoot = onNavigateToAppGraphRoot,
                onNavigateToRegisterScreen = {
                    navController.navigate(Destination.LoginGraph.RegisterScreen) {
                        popUpTo(Destination.LoginGraph.LoginScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}