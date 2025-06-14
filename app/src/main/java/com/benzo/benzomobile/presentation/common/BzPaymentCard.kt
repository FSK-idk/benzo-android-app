package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.getFuelTypeName
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun BzPaymentCard(
    modifier: Modifier = Modifier,
    payment: Payment,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        .format(payment.dateTime),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    getFuelTypeName(payment.fuelType),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
            Text(
                "%.2f ₽".format(payment.paymentAmount / 100.0f),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun BzPaymentCardPreview() {
    BenzoMobileTheme {
        Surface {
            BzPaymentCard(
                modifier = Modifier.fillMaxWidth(),
                payment = Payment(
                    dateTime = ZonedDateTime.parse("2025-06-09T07:48:25.262574+10:00"),
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
                onClick = {},
            )
        }
    }
}