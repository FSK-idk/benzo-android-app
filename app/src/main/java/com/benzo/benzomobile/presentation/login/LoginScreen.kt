package com.benzo.benzomobile.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginValue: String,
    onLoginValueChange: (String) -> Unit,
    isLoginValidationError: Boolean,
    loginValidationError: String,
    passwordValue: String,
    onPasswordValueChange: (String) -> Unit,
    isPasswordValidationError: Boolean,
    passwordValidationError: String,
    isPasswordShown: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = onBackClick,
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                        imageVector = Icons.Default.ChevronLeft,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                    )
                }
            }
        }
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
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )

            OutlinedTextField(
                modifier = Modifier
                    .size(250.dp, 80.dp),
                value = loginValue,
                onValueChange = onLoginValueChange,
                isError = isLoginValidationError,
                label = {
                    Text(
                        text = "Login"
                    )
                },
                supportingText = {
                    if (isLoginValidationError) {
                        Text(
                            text = loginValidationError,
                        )
                    }
                },
                singleLine = true,
            )

            OutlinedTextField(
                modifier = Modifier.size(250.dp, 80.dp),
                value = passwordValue,
                onValueChange = onPasswordValueChange,
                isError = isPasswordValidationError,
                label = {
                    Text(
                        text = "Password",
                    )
                },
                visualTransformation = if (isPasswordShown) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                supportingText = {
                    if (isPasswordValidationError) {
                        Text(
                            text = passwordValidationError,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = onPasswordVisibilityClick,
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = if (isPasswordShown) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = "",
                        )
                    }
                },
                singleLine = true,
            )

            Button(
                modifier = Modifier.size(250.dp, 50.dp),
                onClick = onLoginClick,
            ) {
                Text(
                    text = "Login",
                )
            }

            Text(
                modifier = Modifier,
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
                fontSize = 12.sp,
            )
        }

    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    BenzoMobileTheme {
        Surface {
            LoginScreen(
                loginValue = "",
                onLoginValueChange = {},
                isLoginValidationError = false,
                loginValidationError = "",
                passwordValue = "",
                onPasswordValueChange = {},
                isPasswordValidationError = false,
                passwordValidationError = "",
                isPasswordShown = false,
                onPasswordVisibilityClick = {},
                onBackClick = {},
                onLoginClick = {},
                onRegisterClick = {},
            )
        }
    }
}