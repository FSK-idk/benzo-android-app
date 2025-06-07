package com.benzo.benzomobile.presentation.screen.stations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

// todo: rename to gas stations
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationsScreen(
    modifier: Modifier = Modifier,
    gasStations: List<GasStation>,
    onGasStationClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Gas stations")
                },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(gasStations) { info ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onGasStationClick(info.id) },
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Text(
                            text = "Gas Station №${info.id}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = info.address,
                            fontSize = 16.sp,
                            minLines = 2,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun StationsScreenPreview() {
    val gasStations = listOf<GasStation>(
        GasStation(1, "dom 123"),
        GasStation(2, "dom 223".repeat(10)),
        GasStation(3, "dom 323".repeat(40)),
    )

    BenzoMobileTheme {
        Surface {
            StationsScreen(
                gasStations = gasStations,
                onGasStationClick = {},
            )
        }
    }
}