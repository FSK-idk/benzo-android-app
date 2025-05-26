package com.benzo.benzomobile.presentation.welcome

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun WelcomeScreenRoot(
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    Scaffold { innerPadding ->
        WelcomeScreen(
            modifier = Modifier.padding(innerPadding),
            onRegisterClick = onNavigateToRegisterScreen,
            onLoginClick = onNavigateToLoginScreen,
        )
    }
}