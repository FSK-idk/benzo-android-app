package com.benzo.benzomobile.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.presentation.common.LoadingBox
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    selectedTheme: ThemeOption,
    onThemeClick: (ThemeOption) -> Unit,
    onBackClick: () -> Unit,
) {
    val themes = listOf(ThemeOption.SYSTEM, ThemeOption.LIGHT, ThemeOption.DARK)

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                title = "Settings",
                onBackClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
    ) { innerPadding ->
        LoadingBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isLoading = isLoading,
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Card {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = "Theme",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        Column(
                            modifier = Modifier.selectableGroup(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            themes.forEach {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                            selected = it == selectedTheme,
                                            onClick = { onThemeClick(it) },
                                            role = Role.RadioButton
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = getThemeName(it),
                                        style = MaterialTheme.typography.bodyLarge,
                                    )

                                    Spacer(modifier = Modifier.weight(1.0f))

                                    RadioButton(
                                        selected = it == selectedTheme,
                                        onClick = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun getThemeName(theme: ThemeOption) =
    when (theme) {
        ThemeOption.SYSTEM -> "System"
        ThemeOption.LIGHT -> "Light"
        ThemeOption.DARK -> "Dark"
    }

@Composable
@Preview
fun SettingsScreenPreview() {
    BenzoMobileTheme {
        Surface {
            SettingsScreen(
                isLoading = false,
                selectedTheme = ThemeOption.SYSTEM,
                onThemeClick = {},
                onBackClick = {},
            )
        }
    }
}