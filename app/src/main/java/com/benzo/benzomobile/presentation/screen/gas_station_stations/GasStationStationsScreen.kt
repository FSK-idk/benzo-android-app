package com.benzo.benzomobile.presentation.screen.gas_station_stations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzStationBottomSheet
import com.benzo.benzomobile.presentation.common.BzStationCard
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationStationsScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    gasStation: GasStation,
    stations: List<Station>,
    onTakeClick: (Station) -> Unit,
    isTakeAvailable: Boolean,
    onBackClick: () -> Unit,
) {
    var selectedStation by rememberSaveable { mutableStateOf<Station?>(null) }

    if (selectedStation != null) {
        BzStationBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            station = selectedStation!!,
            onDismiss = { selectedStation = null },
            onTake = {
                onTakeClick(selectedStation!!)
                selectedStation = null
            },
            isTakeAvailable = isTakeAvailable,
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Колонки",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        when (loadStatus) {
            is LoadStatus.Loading -> {
                BzLoadingBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadStatus.Error -> {
                BzErrorBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    message = loadStatus.message,
                    onRetry = onRetry,
                    isRetryAvailable = isRetryAvailable,
                )
            }

            is LoadStatus.Loaded -> {
                BzPullToRefreshBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    onRefresh = onRefresh,
                    isRefreshing = isRefreshing,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "АЗС №${gasStation.id}",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                textAlign = TextAlign.Center,
                            )

                            Text(
                                text = gasStation.address,
                                textAlign = TextAlign.Center,
                                minLines = 2,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(stations) { station ->
                                BzStationCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    station = station,
                                    onClick = { selectedStation = station },
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
    val gasStation = GasStation(12, "dom 17")
    val stations = listOf(
        Station(10, StationStatus.FREE, listOf()),
        Station(11, StationStatus.FREE, listOf()),
        Station(13, StationStatus.NOT_WORKING, listOf()),
    )

    BenzoMobileTheme {
        GasStationStationsScreen(
            isRefreshing = false,
            gasStation = gasStation,
            stations = stations,
            isTakeAvailable = true,
            onTakeClick = { },
            snackbarHostState = SnackbarHostState(),
            onRefresh = {},
            onBackClick = {},
            modifier = Modifier,
            loadStatus = LoadStatus.Loaded,
            onRetry = {},
            isRetryAvailable = true,
        )
    }
}