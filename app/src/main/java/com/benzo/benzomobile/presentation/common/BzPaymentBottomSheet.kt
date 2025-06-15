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
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.getFuelTypeName
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzPaymentBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    payment: Payment,
    onDismiss: () -> Unit,
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
                    Text(text = "АЗС №${payment.gasStation.id} Колонка №${payment.stationId}")
                    Text(text = "Адрес: ${payment.gasStation.address}")
                    Text(
                        text = "Дата и время: ${
                            payment.dateTime
                                .toLocalDateTime(TimeZone.currentSystemDefault())
                                .format(LocalDateTime.Format {
                                    dayOfMonth()
                                    char('.')
                                    monthNumber()
                                    char('.')
                                    year()
                                    char(' ')
                                    hour()
                                    char(':')
                                    minute()
                                    char(':')
                                    second()
                                })
                        }"
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(text = "Номер машины: ${payment.carNumber}")
                    Text(
                        text = "Топливо: ${getFuelTypeName(payment.fuelType)} ${
                            "%.2f".format(payment.fuelAmount / 100.0f)
                        } л."
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(text = "Сумма: ${"%.2f".format(payment.paymentAmount / 100.0f)} ₽")
                    Text(text = "Использовано бонусов: ${"%.2f".format(payment.bonusesUsed / 100.0f)}")
                    Text(text = "Ключ оплаты: ${payment.paymentKey}")
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BzPaymentBottomSheetPreview() {
    BenzoMobileTheme {
        Surface {
            BzPaymentBottomSheet(
                modifier = Modifier,
                sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded),
                payment = Payment(
                    dateTime = Instant.parse("2025-06-09T07:48:25.262574+10:00"),
                    gasStation = GasStation(
                        id = 4,
                        address = "dom 123"
                    ),
                    stationId = 1,
                    fuelType = FuelType.PETROL_95,
                    fuelAmount = 413,
                    carNumber = "A123XA12",
                    paymentAmount = 100234,
                    bonusesUsed = 12331,
                    paymentKey = "nfr8792hbc97g287h3bc7168",
                ),
                onDismiss = {},
            )
        }
    }
}