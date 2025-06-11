package com.benzo.benzomobile.presentation.screen.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onCancelRefuelingClick: () -> Unit,
    onSendClick: () -> Unit,
    onStopClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically,
            )
        ) {
            Button(
                onClick = onStartClick
            ) {
                Text("Start")
            }


            Button(
                onClick = onCancelRefuelingClick
            ) {
                Text("Cancel Refueling")
            }


            Button(
                onClick = onSendClick
            ) {
                Text("Send")
            }


            Button(
                onClick = onStopClick
            ) {
                Text("Stop")
            }
        }
    }
}

@Composable
@Preview
fun TestScreenPreview() {
    BenzoMobileTheme {
        TestScreen(
            onStartClick = {},
            onCancelRefuelingClick = {},
            onSendClick = {},
            onStopClick = {},
        )
    }
}