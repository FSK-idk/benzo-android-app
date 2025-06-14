package com.benzo.benzomobile.presentation.screen.gas_stations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzFilterBottomSheet
import com.benzo.benzomobile.presentation.common.BzGasStationCard
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzOutlinedButton
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationsScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    gasStations: List<GasStation>,
    onGasStationClick: (GasStation) -> Unit,
    onSearch: (FilterQuery) -> Unit,
    isSearchAvailable: Boolean,
) {
    var isFilterShown by rememberSaveable { mutableStateOf(false) }
    var prefixId by rememberSaveable { mutableStateOf("") }
    var prefixAddress by rememberSaveable { mutableStateOf("") }

    if (isFilterShown) {
        BzFilterBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            prefixId = prefixId,
            onPrefixIdChange = { prefixId = it },
            prefixAddress = prefixAddress,
            onPrefixAddressChange = { prefixAddress = it },
            onDismiss = { isFilterShown = false },
            onSearch = {
                isFilterShown = false
                onSearch(it)
            },
            isSearchAvailable = isSearchAvailable,
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = { BzTopAppBar(title = "АЗС") },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        BzOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { isFilterShown = true },
                            text = "Фильтр",
                        )

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(gasStations) { gasStation ->
                                BzGasStationCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    gasStation = gasStation,
                                    onClick = { onGasStationClick(gasStation) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun GasStationsScreenPreview() {
    val gasStations = listOf<GasStation>(
        GasStation(1, "dom 123"),
        GasStation(2, "dom 223".repeat(10)),
        GasStation(3, "dom 323".repeat(40)),
    )

    BenzoMobileTheme {
        Surface {
            GasStationsScreen(
                modifier = Modifier,
                loadStatus = LoadStatus.Loaded,
                onRetry = {},
                isRetryAvailable = true,
                onRefresh = {},
                isRefreshing = false,
                snackbarHostState = SnackbarHostState(),
                gasStations = gasStations,
                onGasStationClick = {},
                onSearch = {},
                isSearchAvailable = true,
            )
        }
    }
}