package com.benzo.benzomobile.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    login: String,
    onLoginChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordShown: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    isLoginAvailable: Boolean,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Вход",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Text(
                text = "С возвращением",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
            )

            BzOutlinedTextField(
                modifier = Modifier.width(250.dp),
                label = "Логин",
                value = login,
                onValueChange = onLoginChange,
            )

            BzOutlinedTextField(
                modifier = Modifier.width(250.dp),
                label = "Пароль",
                value = password,
                onValueChange = onPasswordChange,
                isPassword = !isPasswordShown,
                onVisibilityClick = onPasswordVisibilityClick,
            )

            BzButton(
                modifier = Modifier.width(250.dp),
                onClick = onLoginClick,
                text = "Войти",
                isAvailable = isLoginAvailable,
            )

            Text(
                text = buildAnnotatedString {
                    append("Нет аккаунта? ")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                )
                            ),
                            linkInteractionListener = { onRegisterClick() },
                        )
                    ) {
                        append("Регистрация")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    BenzoMobileTheme {
        LoginScreen(
            snackbarHostState = SnackbarHostState(),
            login = "",
            onLoginChange = {},
            password = "",
            onPasswordChange = {},
            isPasswordShown = false,
            onPasswordVisibilityClick = {},
            onBackClick = {},
            onLoginClick = {},
            isLoginAvailable = true,
            onRegisterClick = {},
        )
    }
}