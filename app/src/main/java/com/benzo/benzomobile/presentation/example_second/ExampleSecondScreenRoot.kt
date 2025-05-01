package com.benzo.benzomobile.presentation.example_second

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.benzo.benzomobile.presentation.loading.ErrorScreen
import com.benzo.benzomobile.presentation.loading.LoadState
import com.benzo.benzomobile.presentation.loading.LoadingScreen

@Composable
fun ExampleSecondScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ExampleSecondScreenViewModel,
) {
    val loadState = viewModel.loadState.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }

        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
            ) { innerPadding ->
                ExampleSecondScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackClick = viewModel::onBackClick,
                )
            }
        }

        LoadState.Error -> {
            ErrorScreen(onRetryClick = viewModel::onRetryClick)
        }
    }
}