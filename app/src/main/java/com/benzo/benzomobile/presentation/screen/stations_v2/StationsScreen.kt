package com.benzo.benzomobile.presentation.screen.stations_v2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationsScreen(
    modifier: Modifier = Modifier,
    gasStation: GasStation,
    stations: List<Station>,
) {
    val isBottomSheetVisible = false
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Stations")
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Gas Station №${gasStation.id}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = gasStation.address,
                fontSize = 16.sp,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(stations) { item ->
                    Card {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .height(80.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Station №${item.id}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )

                            Text(
                                text = "Status: ${item.status}",
                                fontSize = 16.sp,
                                minLines = 1,
                                maxLines = 1,
                            )
                        }
                    }
                }
            }
        }

        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {},
            ) {
                Text(
                    text = "Station №",
                    fontSize = 30.sp
                )
                Text(
                    text = "Available fuel",
                    fontSize = 30.sp
                )
                LazyColumn {
                    item {
                        Text("Fuel DT, Price $10")
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
        Station(13, StationStatus.FREE, listOf()),
    )
    BenzoMobileTheme {
        Surface {
            StationsScreen(
                gasStation = gasStation,
                stations = stations
            )
        }
    }
}