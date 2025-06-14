package com.benzo.benzomobile.presentation.screen.register

import android.content.res.Configuration
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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    login: String,
    onLoginChange: (String) -> Unit,
    loginError: String?,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String?,
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
    repeatPasswordError: String?,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    isRegisterAvailable: Boolean,
    onLoginClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Регистрация",
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
                text = "Новый аккаунт",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
            )

            BzOutlinedTextField(
                modifier = Modifier.width(250.dp),
                label = "Логин",
                value = login,
                onValueChange = onLoginChange,
                valueError = loginError,
            )

            BzOutlinedTextField(
                modifier = Modifier.width(250.dp),
                label = "Пароль",
                value = password,
                onValueChange = onPasswordChange,
                valueError = passwordError,
                isPassword = true,
            )

            BzOutlinedTextField(
                modifier = Modifier.width(250.dp),
                label = "Повторите пароль",
                value = repeatPassword,
                onValueChange = onRepeatPasswordChange,
                valueError = repeatPasswordError,
                isPassword = true,
            )

            BzButton(
                modifier = Modifier.width(250.dp),
                onClick = onRegisterClick,
                text = "Регистрация",
                isAvailable = isRegisterAvailable,
            )

            Text(
                text = buildAnnotatedString {
                    append("Уже есть аккаунт? ")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                )
                            ),
                            linkInteractionListener = { onLoginClick() },
                        )
                    ) {
                        append("Войти")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun RegisterScreenPreview() {
    BenzoMobileTheme {
        RegisterScreen(
            snackbarHostState = SnackbarHostState(),
            login = "",
            onLoginChange = {},
            loginError = null,
            password = "",
            onPasswordChange = {},
            passwordError = null,
            repeatPassword = "",
            onRepeatPasswordChange = {},
            repeatPasswordError = null,
            onBackClick = {},
            onRegisterClick = {},
            isRegisterAvailable = true,
            onLoginClick = {},
        )
    }
}