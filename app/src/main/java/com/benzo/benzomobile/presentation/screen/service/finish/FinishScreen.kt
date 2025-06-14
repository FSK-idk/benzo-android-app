package com.benzo.benzomobile.presentation.screen.service.finish

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishScreen(
    modifier: Modifier = Modifier,
    finishMessage: String,
    onConfirmClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = finishMessage,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.weight(1.5f))

            BzButton(
                modifier = Modifier.width(250.dp),
                onClick = onConfirmClick,
                text = "Завершить",
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun FuelSelectionScreenPreview() {
    BenzoMobileTheme {
        FinishScreen(
            finishMessage = "Ждём вас снова",
            onConfirmClick = {},
        )
    }
}