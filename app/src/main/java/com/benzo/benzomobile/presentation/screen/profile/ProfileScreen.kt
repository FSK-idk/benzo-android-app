package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzOutlinedButton
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    name: String,
    login: String,
    onHistoryClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onExitClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { BzTopAppBar(title = "Профиль") },
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
                BzErrorBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    message = loadStatus.message,
                    onRetry = onRetry,
                    isRetryAvailable = isRetryAvailable,
                )
            }

            is LoadStatus.Loaded -> {
                BzPullToRefreshBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    onRefresh = onRefresh,
                    isRefreshing = isRefreshing,
                ) {
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
                                .background(MaterialTheme.colorScheme.tertiaryContainer),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
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

                        BzButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onHistoryClick,
                            text = "История платежей",
                            isStyled = true,
                        )

                        BzButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onEditProfileClick,
                            text = "Редактирование профиля",
                            isStyled = true,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        BzOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onSettingsClick,
                            text = "Настройки",
                            isStyled = true,
                        )

                        BzOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onExitClick,
                            text = "Выход",
                            isStyled = true,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            ProfileScreen(
                modifier = Modifier,
                loadStatus = LoadStatus.Loaded,
                onRetry = {},
                isRetryAvailable = true,
                onRefresh = {},
                isRefreshing = true,
                snackbarHostState = SnackbarHostState(),
                name = "Ivan Ivanov",
                login = "ivanivanov",
                onHistoryClick = {},
                onEditProfileClick = {},
                onSettingsClick = {},
                onExitClick = {},
            )
        }
    }
}


