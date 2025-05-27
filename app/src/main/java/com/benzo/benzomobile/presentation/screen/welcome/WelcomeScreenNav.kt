package com.benzo.benzomobile.presentation.screen.welcome

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination

fun NavGraphBuilder.welcomeScreen(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<Destination.LoginGraph.WelcomeScreen> {
        Scaffold { innerPadding ->
            WelcomeScreen(
                modifier = Modifier.padding(innerPadding),
                onRegisterClick = onNavigateToRegisterScreen,
                onLoginClick = onNavigateToLoginScreen,
            )
        }
    }
}