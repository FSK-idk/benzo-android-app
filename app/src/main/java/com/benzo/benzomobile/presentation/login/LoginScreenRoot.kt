package com.benzo.benzomobile.presentation.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAppRoot: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    val loginValue = viewModel.loginValue.collectAsState()
    val isLoginValidationError = viewModel.isLoginValidationError.collectAsState()
    val loginValidationError = viewModel.loginValidationError.collectAsState()
    val passwordValue = viewModel.passwordValue.collectAsState()
    val isPasswordValidationError = viewModel.isPasswordValidationError.collectAsState()
    val passwordValidationError = viewModel.passwordValidationError.collectAsState()
    val isPasswordShown = viewModel.isPasswordShown.collectAsState()

    Scaffold { innerPadding ->
        LoginScreen(
            modifier = Modifier.padding(innerPadding),
            loginValue = loginValue.value,
            onLoginValueChange = viewModel::onLoginValueChange,
            isLoginValidationError = isLoginValidationError.value,
            loginValidationError = loginValidationError.value,
            passwordValue = passwordValue.value,
            onPasswordValueChange = viewModel::onPasswordValueChange,
            isPasswordValidationError = isPasswordValidationError.value,
            passwordValidationError = passwordValidationError.value,
            isPasswordShown = isPasswordShown.value,
            onPasswordVisibilityClick = viewModel::onPasswordVisibilityClick,
            onBackClick = onNavigateBack,
            onLoginClick = onNavigateToAppRoot,
            onRegisterClick = onNavigateToRegisterScreen,
        )
    }
}