package com.benzo.benzomobile.presentation.screen.register

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

        RegisterScreen(
            login = uiState.value.login,
            onLoginChange = viewModel::onLoginChange,
            loginError = uiState.value.loginError,
            password = uiState.value.password,
            onPasswordChange = viewModel::onPasswordChange,
            passwordError = uiState.value.passwordError,
            confirmPassword = uiState.value.confirmPassword,
            onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
            confirmPasswordError = uiState.value.confirmPasswordError,
            isLoading = uiState.value.isRegisterAvailable,
            snackbarHostState = loadState.value.snackbarHostState,
            onBackClick = onNavigateBack,
            onRegisterClick = { viewModel.onRegisterClicked(onNavigateToAppGraphRoot) },
            onLoginClick = onNavigateToLoginScreen,
        )
    }
}