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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch

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
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    var idQuery by remember { mutableStateOf("") }
    var addressQuery by remember { mutableStateOf("") }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = idQuery,
                    onValueChange = { idQuery = it },
                    label = { Text("Поиск по ID") },
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = addressQuery,
                    onValueChange = { addressQuery = it },
                    label = { Text("Поиск по адресу") },
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = {
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                showSheet = false
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Искать")
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { SimpleTopAppBar(title = "АЗС") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = { showSheet = true }
            ) {
                Text("Фильтр")
            }

            PullToRefreshBox(
                modifier = Modifier
                    .weight(1f),
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