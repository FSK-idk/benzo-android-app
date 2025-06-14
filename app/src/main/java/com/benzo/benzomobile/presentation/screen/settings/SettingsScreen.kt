package com.benzo.benzomobile.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.model.getThemeName
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzRadioOption
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    snackbarHostState: SnackbarHostState,
    selectedTheme: ThemeOption,
    onThemeClick: (ThemeOption) -> Unit,
    onBackClick: () -> Unit,
) {
    val themes = listOf(
        ThemeOption.SYSTEM,
        ThemeOption.LIGHT,
        ThemeOption.DARK
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Настройки",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        when (loadStatus) {
            is LoadStatus.Loading -> {
                BzLoadingBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadStatus.Error -> {
                BzLoadingBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadStatus.Loaded -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                        ) {
                            Text(
                                text = "Тема",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primaryContainer,
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                            Column(
                                modifier = Modifier.selectableGroup(),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                themes.forEach { theme ->
                                    BzRadioOption(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = { onThemeClick(theme) },
                                        text = getThemeName(theme),
                                        isSelected = theme == selectedTheme,
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

@Preview
@Composable
fun SettingsScreenPreview() {
    BenzoMobileTheme {
        Surface {
            SettingsScreen(
                modifier = Modifier,
                loadStatus = LoadStatus.Loaded,
                snackbarHostState = SnackbarHostState(),
                selectedTheme = ThemeOption.SYSTEM,
                onThemeClick = {},
                onBackClick = {},
            )
        }
    }
}