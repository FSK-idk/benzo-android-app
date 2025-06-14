package com.benzo.benzomobile.presentation.screen.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzOutlinedButton
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "BENZO",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayLarge,
            )

            Spacer(modifier = Modifier.weight(1.5f))

            BzOutlinedButton(
                modifier = Modifier.width(250.dp),
                onClick = onRegisterClick,
                text = "Регистрация",
            )

            BzButton(
                modifier = Modifier.width(250.dp),
                onClick = onLoginClick,
                text = "Войти",
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun WelcomeScreenPreview() {
    BenzoMobileTheme {
        WelcomeScreen(
            onRegisterClick = {},
            onLoginClick = {},
        )
    }
}