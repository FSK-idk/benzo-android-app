package com.benzo.benzomobile.presentation.screen.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            LoginScreen(
                modifier = Modifier.padding(innerPadding),
                login = uiState.value.login,
                onLoginChange = viewModel::onLoginChange,
                isLoginValidationError = uiState.value.isLoginValidationError,
                loginValidationErrorMessage = uiState.value.loginValidationErrorMessage,
                password = uiState.value.password,
                onPasswordChange = viewModel::onPasswordChange,
                isPasswordValidationError = uiState.value.isPasswordValidationError,
                passwordValidationErrorMessage = uiState.value.passwordValidationErrorMessage,
                isPasswordShown = uiState.value.isPasswordShown,
                onPasswordVisibilityClick = viewModel::onPasswordVisibilityClick,
                onBackClick = onNavigateBack,
                onLoginClick = onNavigateToAppGraphRoot,
                onRegisterClick = onNavigateToRegisterScreen,
            )
        }
    }
}