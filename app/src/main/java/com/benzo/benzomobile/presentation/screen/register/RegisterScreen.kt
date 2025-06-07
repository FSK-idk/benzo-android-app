package com.benzo.benzomobile.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    login: String,
    onLoginChange: (String) -> Unit,
    loginError: String?,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String?,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    confirmPasswordError: String?,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Registration") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
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
                text = "New account",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
            )

            OutlinedTextField(
                modifier = Modifier.width(250.dp),
                value = login,
                onValueChange = onLoginChange,
                label = { Text(text = "Login") },
                singleLine = true,
                isError = loginError != null,
                supportingText = {
                    loginError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
            )

            OutlinedTextField(
                modifier = Modifier.width(250.dp),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError != null,
                supportingText = {
                    passwordError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
            )

            OutlinedTextField(
                modifier = Modifier.width(250.dp),
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = { Text(text = "Confirm password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = confirmPasswordError != null,
                supportingText = {
                    confirmPasswordError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
            )

            Button(
                modifier = Modifier.width(250.dp),
                onClick = onRegisterClick,
                enabled = !isLoading,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(25.dp))
                } else {
                    Text(text = "Register")
                }
            }

            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            ),
                            linkInteractionListener = { onLoginClick() },
                        )
                    ) {
                        append("Login")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
@Preview
fun RegisterScreenPreview() {
    BenzoMobileTheme {
        RegisterScreen(
            login = "",
            onLoginChange = {},
            loginError = null,
            password = "",
            onPasswordChange = {},
            passwordError = null,
            confirmPassword = "",
            onConfirmPasswordChange = {},
            confirmPasswordError = null,
            isLoading = false,
            snackbarHostState = SnackbarHostState(),
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {},
        )
    }
}