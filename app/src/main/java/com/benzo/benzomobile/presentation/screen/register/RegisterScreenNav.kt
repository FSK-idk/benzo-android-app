package com.benzo.benzomobile.presentation.screen.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            RegisterScreen(
                modifier = Modifier.padding(innerPadding),
                login = uiState.value.login,
                onLoginChange = viewModel::onLoginChange,
                isLoginValidationError = uiState.value.isLoginValidationError,
                loginValidationErrorMessage = uiState.value.loginValidationErrorMessage,
                password = uiState.value.password,
                onPasswordChange = viewModel::onPasswordChange,
                isPasswordValidationError = uiState.value.isPasswordValidationError,
                passwordValidationErrorMessage = uiState.value.passwordValidationErrorMessage,
                confirmPassword = uiState.value.confirmPassword,
                onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
                isConfirmPasswordValidationError = uiState.value.isConfirmPasswordValidationError,
                confirmPasswordValidationErrorMessage = uiState.value.confirmPasswordValidationErrorMessage,
                onBackClick = onNavigateBack,
                onRegisterClick = onNavigateToAppGraphRoot,
                onLoginClick = onNavigateToLoginScreen,
            )
        }
    }
}