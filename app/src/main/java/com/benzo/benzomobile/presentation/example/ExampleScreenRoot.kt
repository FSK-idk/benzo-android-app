package com.benzo.benzomobile.presentation.example

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.benzo.benzomobile.presentation.loading.ErrorScreen
import com.benzo.benzomobile.presentation.loading.LoadState
import com.benzo.benzomobile.presentation.loading.LoadingScreen

@Composable
fun ExampleScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ExampleScreenViewModel,
) {
    val loadState = viewModel.loadState.collectAsState()
    val text = viewModel.text.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }

        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
            ) { innerPadding ->
                ExampleScreen(
                    modifier = Modifier.padding(innerPadding),
                    text = text.value,
                    onButtonClick = viewModel::onButtonClick,
                    onNextClick = viewModel::onNextClick,
                )
            }
        }

        LoadState.Error -> {
            ErrorScreen(onRetryClick = viewModel::onRetryClick)
        }
    }
}