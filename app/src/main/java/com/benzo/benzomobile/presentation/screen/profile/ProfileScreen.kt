package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.R
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.datetime.LocalDate

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    icon: Int,
    name: String,
    phoneNumber: String,
    email: String,
    registrationDate: LocalDate,
    onSettingsClick: () -> Unit,
    onEditClick: () -> Unit,
    onExitClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Spacer(
            modifier = Modifier.height(30.dp),
        )

        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                painter = painterResource(icon),
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = name,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(
            modifier = Modifier.height(16.dp),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Phone: $phoneNumber",
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(
                modifier = Modifier.height(8.dp),
            )

            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(
                modifier = Modifier.height(8.dp),
            )

            Text(
                text = "Registration date: $registrationDate",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SettingsButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Settings",
                onClick = onSettingsClick,
            )

            SettingsButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Edit",
                onClick = onEditClick,
            )
            SettingsButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Exit",
                onClick = onExitClick,
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            ProfileScreen(
                icon = R.drawable.ic_launcher_foreground,
                name = "Ivan Ivanov",
                phoneNumber = "+7 999 123-45-67",
                email = "ivan@example.com",
                registrationDate = LocalDate(
                    year = 2025,
                    monthNumber = 1,
                    dayOfMonth = 1,
                ),
                onSettingsClick = {},
                onEditClick = {},
                onExitClick = {},
            )
        }
    }
}


