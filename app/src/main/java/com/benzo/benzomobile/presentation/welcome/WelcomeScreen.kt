package com.benzo.benzomobile.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "BENZO",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.weight(1.5f))

        OutlinedButton(
            modifier = Modifier.size(250.dp, 50.dp),
            onClick = onRegisterClick,
        ) {
            Text(
                text = "Register",
            )
        }

        Button(
            modifier = Modifier.size(250.dp, 50.dp),
            onClick = onLoginClick,
        ) {
            Text(
                text = "Login",
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun WelcomeScreenPreview() {
    BenzoMobileTheme {
        Surface {
            WelcomeScreen(
                onRegisterClick = {},
                onLoginClick = {},
            )
        }
    }
}