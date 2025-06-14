package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.domain.model.getFuelTypeName
import com.benzo.benzomobile.domain.model.getStationStatusName
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzStationBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    station: Station,
    onDismiss: () -> Unit,
    onTake: () -> Unit,
    isTakeAvailable: Boolean,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Подробная информация",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primaryContainer,
            )

            Spacer(modifier = Modifier.size(20.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(text = "Колонка №${station.id}")
                    Text(text = "Статус: ${getStationStatusName(station.status)}")
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(
                        text = "Топливо:"
                    )

                    station.fuels.forEach {
                        Text(
                            text = "• ${getFuelTypeName(it.type)}: ${"%.2f ₽".format(it.price / 100.0f)}"
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)

                BzButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onTake,
                    text = "Занять",
                    isAvailable = isTakeAvailable,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BzStationBottomSheetPreview() {
    BenzoMobileTheme {
        Surface {
            BzStationBottomSheet(
                modifier = Modifier,
                sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded),
                Station(
                    10, StationStatus.FREE, listOf(
                        Fuel(FuelType.PETROL_95, 1234),
                        Fuel(FuelType.DIESEL, 1234),
                    )
                ),
                onDismiss = {},
                onTake = {},
                isTakeAvailable = true,
            )
        }
    }
}