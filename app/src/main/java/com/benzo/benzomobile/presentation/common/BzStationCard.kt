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
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.domain.model.getStationStatusName
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzStationCard(
    modifier: Modifier = Modifier,
    station: Station,
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
                text = "Колонка №${station.id}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primaryContainer,
            )

            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = "Статус: ${getStationStatusName(station.status)}",
                style = MaterialTheme.typography.bodySmall,
                minLines = 1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun BzStationCardPreview() {
    BenzoMobileTheme {
        Surface {
            BzStationCard(
                modifier = Modifier.fillMaxWidth(),
                station = Station(
                    id = 12,
                    status = StationStatus.BUSY_ONLINE,
                    fuels = listOf(),
                ),
                onClick = {},
            )
        }
    }
}