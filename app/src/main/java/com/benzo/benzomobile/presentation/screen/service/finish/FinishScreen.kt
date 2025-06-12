package com.benzo.benzomobile.presentation.screen.service.finish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.presentation.common.FuelAmountSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.FuelTypeSimpleOutlinedTextFiled
import com.benzo.benzomobile.presentation.common.MoneySimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishScreen(
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = "Ждём вас снова",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Button(
                modifier = Modifier.width(250.dp),
                onClick = onConfirmClick,
            ) {
                Text(text = "Завершить")
            }

            Spacer(modifier = Modifier.weight(1.0f))
        }
    }
}


@Composable
@Preview
fun FuelSelectionScreenPreview() {
    BenzoMobileTheme {
        FinishScreen(
            onConfirmClick = {},
        )
    }
}