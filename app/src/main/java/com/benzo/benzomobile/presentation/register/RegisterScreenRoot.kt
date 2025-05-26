package com.benzo.benzomobile.presentation.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterScreenViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAppRoot: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    val loginValue = viewModel.loginValue.collectAsState()
    val isLoginValidationError = viewModel.isLoginValidationError.collectAsState()
    val loginValidationError = viewModel.loginValidationError.collectAsState()
    val passwordValue = viewModel.passwordValue.collectAsState()
    val isPasswordValidationError = viewModel.isPasswordValidationError.collectAsState()
    val passwordValidationError = viewModel.passwordValidationError.collectAsState()
    val confirmPasswordValue = viewModel.confirmPasswordValue.collectAsState()
    val isConfirmPasswordValidationError = viewModel.isConfirmPasswordValidationError.collectAsState()
    val confirmPasswordValidationError = viewModel.confirmPasswordValidationError.collectAsState()

    Scaffold { innerPadding ->
        RegisterScreen(
            modifier = Modifier.padding(innerPadding),
            loginValue = loginValue.value,
            onLoginValueChange = viewModel::onLoginValueChange,
            isLoginValidationError = isLoginValidationError.value,
            loginValidationError = loginValidationError.value,
            passwordValue = passwordValue.value,
            onPasswordValueChange = viewModel::onPasswordValueChange,
            isPasswordValidationError = isPasswordValidationError.value,
            passwordValidationError = passwordValidationError.value,
            confirmPasswordValue = confirmPasswordValue.value,
            onConfirmPasswordValueChange = viewModel::onConfirmPasswordValueChange,
            isConfirmPasswordValidationError = isConfirmPasswordValidationError.value,
            confirmPasswordValidationError = confirmPasswordValidationError.value,
            onBackClick = onNavigateBack,
            onRegisterClick = onNavigateToAppRoot,
            onLoginClick = onNavigateToLoginScreen,
        )
    }
}