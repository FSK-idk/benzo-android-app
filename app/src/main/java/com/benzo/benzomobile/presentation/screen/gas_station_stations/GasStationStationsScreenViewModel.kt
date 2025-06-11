package com.benzo.benzomobile.presentation.screen.gas_station_stations

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.use_case.GetGasStationStationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GasStationStationsScreenViewModel(
    private val gasStation: GasStation,
    private val getGasStationStationsUseCase: GetGasStationStationsUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(StationsScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(StationsScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(gasStation = gasStation) }
        viewModelScope.launch {
            _loadState.update { it.copy(isLoading = true) }
            fetchData()
            _loadState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun fetchData() {
        try {
            _uiState.update { it.copy(stations = getGasStationStationsUseCase(gasStation.id)) }
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            _loadState.value.snackbarHostState.showSnackbar(
                message = e.message ?: "Ошибка",
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun onRefresh() {
        if (!_loadState.value.isRefreshing) {
            viewModelScope.launch {
                _loadState.update { it.copy(isRefreshing = true) }
                fetchData()
                _loadState.update { it.copy(isRefreshing = false) }
            }
        }
    }

    fun onTakeClick(station: Station) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTakeAvailable = false) }


            _uiState.update { it.copy(isTakeAvailable = true) }
        }
    }
}

data class StationsScreenLoadState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class StationsScreenUiState(
    val gasStation: GasStation = GasStation(id = 0, address = ""),
    val stations: List<Station> = listOf(),
    val isTakeAvailable: Boolean = true,
)