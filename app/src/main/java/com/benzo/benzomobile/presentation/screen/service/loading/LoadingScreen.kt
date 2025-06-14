package com.benzo.benzomobile.presentation.screen.service.loading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        BzLoadingBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    BenzoMobileTheme {
        LoadingScreen()
    }
}