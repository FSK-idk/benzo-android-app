package com.benzo.benzomobile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.login.LoginScreenRoot
import com.benzo.benzomobile.presentation.login.LoginScreenViewModel
import com.benzo.benzomobile.presentation.register.RegisterScreenRoot
import com.benzo.benzomobile.presentation.register.RegisterScreenViewModel
import com.benzo.benzomobile.presentation.welcome.WelcomeScreenRoot
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loginRoot(
    onNavigateToAppRoot: () -> Unit,
) {
    composable<Destination.LoginRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.WelcomeScreen,
        ) {
            welcomeScreen(
                onNavigateToRegisterScreen = {
                    navController.navigate(Destination.RegisterScreen)
                },
                onNavigateToLoginScreen = {
                    navController.navigate(Destination.LoginScreen)
                },
            )

            registerScreen(
                onNavigateBack = navController::navigateUp,
                onNavigateToAppRoot = onNavigateToAppRoot,
                onNavigateToLoginScreen = {
                    navController.navigate(Destination.LoginScreen) {
                        popUpTo(Destination.RegisterScreen) {
                            inclusive = true
                        }
                    }
                },
            )

            loginScreen(
                onNavigateBack = navController::navigateUp,
                onNavigateToAppRoot = onNavigateToAppRoot,
                onNavigateToRegisterScreen = {
                    navController.navigate(Destination.RegisterScreen) {
                        popUpTo(Destination.LoginScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.welcomeScreen(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<Destination.WelcomeScreen> {
        // TODO: add state?
        WelcomeScreenRoot(
            onNavigateToRegisterScreen = onNavigateToRegisterScreen,
            onNavigateToLoginScreen = onNavigateToLoginScreen,
        )
    }
}

fun NavGraphBuilder.registerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAppRoot: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<Destination.RegisterScreen> {
        val viewModel = koinViewModel<RegisterScreenViewModel>()
        // TODO: add state?
        RegisterScreenRoot(
            viewModel = viewModel,
            onNavigateBack = onNavigateBack,
            onNavigateToAppRoot = onNavigateToAppRoot,
            onNavigateToLoginScreen = onNavigateToLoginScreen,
        )
    }
}

fun NavGraphBuilder.loginScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAppRoot: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    composable<Destination.LoginScreen> {
        val viewModel = koinViewModel<LoginScreenViewModel>()
        // TODO: add state?
        LoginScreenRoot(
            viewModel = viewModel,
            onNavigateBack = onNavigateBack,
            onNavigateToAppRoot = onNavigateToAppRoot,
            onNavigateToRegisterScreen = onNavigateToRegisterScreen,
        )
    }
}