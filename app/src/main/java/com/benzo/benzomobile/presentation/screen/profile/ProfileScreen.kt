package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean,
    name: String,
    login: String,
    snackbarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
    onHistoryClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onExitClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { SimpleTopAppBar(title = "Профиль") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterVertically,
                    ),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = name,
                            style = MaterialTheme.typography.titleLarge,
                        )

                        Text(
                            text = "@$login",
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onHistoryClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "История платежей")

                            Spacer(modifier = Modifier.weight(1.0f))

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                            )
                        }
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onEditProfileClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Редактирование профиля")

                            Spacer(modifier = Modifier.weight(1.0f))

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1.0f))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onSettingsClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Настройки")

                            Spacer(modifier = Modifier.weight(1.0f))

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                            )
                        }
                    }

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onExitClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Выход")

                            Spacer(modifier = Modifier.weight(1.0f))

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            ProfileScreen(
                isLoading = false,
                isRefreshing = false,
                name = "Ivan Ivanov",
                login = "ivanivanov",
                snackbarHostState = SnackbarHostState(),
                onRefresh = {},
                onHistoryClick = {},
                onEditProfileClick = {},
                onSettingsClick = {},
                onExitClick = {},
            )
        }
    }
}


