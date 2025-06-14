package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzErrorBox(
    modifier: Modifier = Modifier,
    message: String? = null,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = message ?: "Ошибка",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1.5f))

        BzButton(
            modifier = Modifier.width(250.dp),
            onClick = onRetry,
            text = "Повторить",
            isAvailable = isRetryAvailable,
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun BzErrorBoxPreview() {
    BenzoMobileTheme {
        Surface {
            BzErrorBox(
                modifier = Modifier.fillMaxSize(),
                message = "Произошла ошибка",
                onRetry = {},
                isRetryAvailable = true,
            )
        }
    }
}