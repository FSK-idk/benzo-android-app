package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzLoadingBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun BzLoadingBoxPreview() {
    BenzoMobileTheme {
        Surface {
            BzLoadingBox(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}