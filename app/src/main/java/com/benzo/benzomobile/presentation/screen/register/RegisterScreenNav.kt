package com.benzo.benzomobile.presentation.screen.register

import androidx.activity.compose.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.registerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAppGraphRoot: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<Destination.LoginGraph.RegisterScreen> {
        val viewModel = koinViewModel<RegisterScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        BackHandler(onBack = onNavigateBack)

        RegisterScreen(
            snackbarHostState = loadState.value.snackbarHostState,
            login = uiState.value.login,
            onLoginChange = viewModel::onLoginChange,
            loginError = uiState.value.loginError,
            password = uiState.value.password,
            onPasswordChange = viewModel::onPasswordChange,
            passwordError = uiState.value.passwordError,
            repeatPassword = uiState.value.repeatPassword,
            onRepeatPasswordChange = viewModel::onConfirmPasswordChange,
            repeatPasswordError = uiState.value.repeatPasswordError,
            onBackClick = onNavigateBack,
            onRegisterClick = { viewModel.onRegisterClicked(navigateNext = onNavigateToAppGraphRoot) },
            isRegisterAvailable = uiState.value.isRegisterAvailable,
            onLoginClick = onNavigateToLoginScreen,
        )
    }
}