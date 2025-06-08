package com.benzo.benzomobile.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    id: Int,
    name: String,
    snackbarHostState: SnackbarHostState,
    onHistoryClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onExitClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { SimpleTopAppBar(title = "Profile") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxSize(),
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
                    text = "id: $id",
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
                    Text(text = "History")

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
                    Text(text = "Edit profile")

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
                    Text(text = "Settings")

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
                    Text(text = "Exit")

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

@Composable
@Preview
fun ProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            ProfileScreen(
                id = 12,
                name = "Ivan Ivanov",
                snackbarHostState = SnackbarHostState(),
                onHistoryClick = {},
                onEditProfileClick = {},
                onSettingsClick = {},
                onExitClick = {},
            )
        }
    }
}


