package com.benzo.benzomobile.presentation.screen.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.welcomeScreen(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<Destination.LoginGraph.WelcomeScreen> {
        WelcomeScreen(
            onRegisterClick = onNavigateToRegisterScreen,
            onLoginClick = onNavigateToLoginScreen,
        )
    }
}