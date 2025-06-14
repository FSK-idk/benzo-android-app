package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzGasStationCard(
    modifier: Modifier = Modifier,
    gasStation: GasStation,
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
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Text(
                text = "АЗС №${gasStation.id}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primaryContainer,
            )

            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = gasStation.address,
                style = MaterialTheme.typography.bodySmall,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun BzGasStationCardPreview() {
    BenzoMobileTheme {
        Surface {
            BzGasStationCard(
                modifier = Modifier.fillMaxWidth(),
                gasStation = GasStation(
                    id = 12,
                    address = "address",
                ),
                onClick = {},
            )
        }
    }
}