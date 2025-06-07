package com.benzo.benzomobile.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    login: String,
    onLoginChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordShown: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Login") },
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
                text = "Welcome back",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
            )

            OutlinedTextField(
                modifier = Modifier.width(250.dp),
                value = login,
                onValueChange = onLoginChange,
                label = { Text(text = "Login") },
                singleLine = true,
            )

            OutlinedTextField(
                modifier = Modifier.width(250.dp),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = if (isPasswordShown) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = onPasswordVisibilityClick,
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = if (isPasswordShown) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = null,
                        )
                    }
                },
            )

            Button(
                modifier = Modifier.width(250.dp),
                onClick = onLoginClick,
                enabled = !isLoading,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(25.dp))
                } else {
                    Text(text = "Login")
                }
            }

            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            ),
                            linkInteractionListener = { onRegisterClick() },
                        )
                    ) {
                        append("Register")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    BenzoMobileTheme {
        LoginScreen(
            login = "",
            onLoginChange = {},
            password = "",
            onPasswordChange = {},
            isPasswordShown = false,
            onPasswordVisibilityClick = {},
            isLoading = false,
            snackbarHostState = SnackbarHostState(),
            onBackClick = {},
            onLoginClick = {},
            onRegisterClick = {},
        )
    }
}