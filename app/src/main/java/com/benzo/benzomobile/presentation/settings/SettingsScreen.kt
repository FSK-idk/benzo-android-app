package com.benzo.benzomobile.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    selectedTheme: String,
    onThemeSelected: (String) -> Unit,
    onBackClick: () -> Unit,
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
                            .background(color = MaterialTheme.colorScheme.primary),
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
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Choose theme",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(30.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ThemeCircleOption(
                    themeName = "Light",
                    color = Color(0xFFF1F1F1),
                    selectedTheme = selectedTheme,
                    onSelected = onThemeSelected,
                )

                Spacer(
                    modifier = Modifier.width(20.dp),
                )

                ThemeCircleOption(
                    themeName = "Dark",
                    color = Color(0xFF1C1C1C),
                    selectedTheme = selectedTheme,
                    onSelected = onThemeSelected,
                )

                Spacer(
                    modifier = Modifier.width(20.dp),
                )

                ThemeCircleOption(
                    themeName = "System",
                    color = Color(0xFF888888),
                    selectedTheme = selectedTheme,
                    onSelected = onThemeSelected,
                )
            }
        }
    }
}


@Composable
fun ThemeCircleOption(
    themeName: String,
    color: Color,
    selectedTheme: String,
    onSelected: (String) -> Unit
) {
    val isSelected = selectedTheme == themeName
    val interactionSource = remember { MutableInteractionSource() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(
                    color = color,
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onSelected(themeName)
                },
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Green,
                )
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp),
        )

        Text(
            text = themeName,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    BenzoMobileTheme {
        Surface {
            SettingsScreen(
                onBackClick = {},
                selectedTheme = "",
                onThemeSelected = {},
            )
        }
    }
}