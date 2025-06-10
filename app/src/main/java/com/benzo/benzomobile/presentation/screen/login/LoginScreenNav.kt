package com.benzo.benzomobile.presentation.screen.login

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loginScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAppGraphRoot: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    composable<Destination.LoginGraph.LoginScreen> {
        val viewModel = koinViewModel<LoginScreenViewModel>()
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        LoginScreen(
            login = uiState.value.login,
            onLoginChange = viewModel::onLoginChange,
            password = uiState.value.password,
            onPasswordChange = viewModel::onPasswordChange,
            isPasswordShown = uiState.value.isPasswordShown,
            onPasswordVisibilityClick = viewModel::onPasswordVisibilityClick,
            isLoading = uiState.value.isLoginAvailable,
            snackbarHostState = loadState.value.snackbarHostState,
            onBackClick = onNavigateBack,
            onLoginClick = { viewModel.onLoginClicked(onNavigateToAppGraphRoot) },
            onRegisterClick = onNavigateToRegisterScreen,
        )
    }
}