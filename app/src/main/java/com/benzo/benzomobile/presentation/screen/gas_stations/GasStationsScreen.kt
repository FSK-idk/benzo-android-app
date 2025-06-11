package com.benzo.benzomobile.presentation.screen.gas_stations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean,
    gasStations: List<GasStation>,
    snackbarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
    onGasStationClick: (GasStation) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { SimpleTopAppBar(title = "АЗС") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(gasStations) { gasStation ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onGasStationClick(gasStation) },
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                Text(
                                    text = "АЗС №${gasStation.id}",
                                    fontWeight = FontWeight.Bold,
                                )

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
            GasStationsScreen(
                isLoading = false,
                isRefreshing = false,
                gasStations = gasStations,
                snackbarHostState = SnackbarHostState(),
                onRefresh = {},
                onGasStationClick = {},
            )
        }
    }
}