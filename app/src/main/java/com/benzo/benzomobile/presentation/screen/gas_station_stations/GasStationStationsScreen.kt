package com.benzo.benzomobile.presentation.screen.gas_station_stations

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationStationsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean,
    gasStation: GasStation,
    stations: List<Station>,
    isTakeAvailable: Boolean,
    onTakeClick: (Station) -> Unit,
    snackbarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
    onBackClick: () -> Unit,
) {
    var selectedStation by rememberSaveable { mutableStateOf<Station?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SimpleTopAppBar(
                title = "Колонки",
                onBackClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "АЗС №${gasStation.id}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = gasStation.address,
                            minLines = 2,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        itemsIndexed(stations) { index, station ->
                            Card(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                onClick = {
                                    selectedStation = station
                                    coroutineScope.launch { sheetState.show() }
                                },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(5.dp),
                                ) {
                                    Text(
                                        text = "Колонка №${station.id}",
                                        fontWeight = FontWeight.Bold,
                                    )

                                    Text(
                                        text = "Статус: ${getStationStatusName(station.status)}",
                                        minLines = 2,
                                        maxLines = 2,
                                    )
                                }
                            }
                        }
                    }
                }

                if (sheetState.isVisible && selectedStation != null) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                selectedStation = null
                            }
                        },
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = "Колонка №${selectedStation!!.id}",
                                fontWeight = FontWeight.Bold,
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                Text(
                                    text = "Статус: ${getStationStatusName(selectedStation!!.status)}"
                                )

                                Text(
                                    text = "Топливо:"
                                )

                                selectedStation!!.fuels.forEach {
                                    Text(
                                        text = "• ${getFuelTypeName(it.type)}: ${
                                            "%.2f".format(it.price / 100.0f)
                                        } ₽"
                                    )
                                }
                            }

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                        val s = selectedStation!!
                                        selectedStation = null
                                        onTakeClick(s)
                                    }
                                },
                                enabled = isTakeAvailable,
                            ) {
                                if (!isTakeAvailable) {
                                    CircularProgressIndicator(modifier = Modifier.size(25.dp))
                                } else {
                                    Text(text = "Занять")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun getStationStatusName(status: StationStatus) =
    when (status) {
        StationStatus.BUSY_OFFLINE -> "Занято"
        StationStatus.BUSY_ONLINE -> "Занято"
        StationStatus.FREE -> "Свободно"
        StationStatus.NOT_WORKING -> "Не работает"
    }

fun getFuelTypeName(fuelType: FuelType) =
    when (fuelType) {
        FuelType.PETROL_92 -> "АИ-92"
        FuelType.PETROL_95 -> "АИ-95"
        FuelType.PETROL_98 -> "АИ-98"
        FuelType.DIESEL -> "ДТ"
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
            isLoading = false,
            isRefreshing = false,
            gasStation = gasStation,
            stations = stations,
            isTakeAvailable = true,
            onTakeClick = { },
            snackbarHostState = SnackbarHostState(),
            onRefresh = {},
            onBackClick = {}
        )
    }
}