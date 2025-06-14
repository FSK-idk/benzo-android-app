package com.benzo.benzomobile.presentation.screen.gas_station_stations

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.Station
import com.benzo.benzomobile.domain.model.StationStatus
import com.benzo.benzomobile.domain.use_case.GetGasStationStationsUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GasStationStationsScreenViewModel(
    private val gasStation: GasStation,
    private val getGasStationStationsUseCase: GetGasStationStationsUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(StationsScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(StationsScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(gasStation = gasStation) }
        viewModelScope.launch {
            try {
                loadData()

                _loadState.update { it.copy(loadStatus = LoadStatus.Loaded) }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.update { it.copy(loadStatus = LoadStatus.Error(message = e.message)) }
            }
        }
    }

    private suspend fun loadData() {
        val stations = getGasStationStationsUseCase(gasStation.id)

        _uiState.update { it.copy(stations = stations) }
    }

    private fun sendMessage(message: String?) {
        viewModelScope.launch {
            _loadState.value.snackbarHostState.showSnackbar(
                message = message ?: "Ошибка",
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun onRetry() {
        viewModelScope.launch {
            _loadState.update { it.copy(isRetryAvailable = false) }

            try {
                loadData()

                _loadState.update { it.copy(loadStatus = LoadStatus.Loaded) }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.update { it.copy(loadStatus = LoadStatus.Error(message = e.message)) }
            }

            _loadState.update { it.copy(isRetryAvailable = true) }
        }
    }

    fun onRefresh() {
        if (!_loadState.value.isRefreshing) {
            viewModelScope.launch {
                _loadState.update { it.copy(isRefreshing = true) }

                try {
                    loadData()
                } catch (e: Exception) {
                    Log.e(TAG, "$e")
                    sendMessage(message = e.message)
                }

                _loadState.update { it.copy(isRefreshing = false) }
            }
        }
    }

    fun onTakeClick(station: Station, onNavigateNext: (Int) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTakeAvailable = false) }

            if (station.status != StationStatus.FREE) {
                _loadState.value.snackbarHostState.showSnackbar(
                    message = "Колонка не доступна в данный момент",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            } else if (getUserUseCase().carNumber == null) {
                _loadState.value.snackbarHostState.showSnackbar(
                    message = "Заполните все данные профиля",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            } else {
                onNavigateNext(station.id)
            }

            _uiState.update { it.copy(isTakeAvailable = true) }
        }
    }
}

data class StationsScreenLoadState(
    val loadStatus: LoadStatus = LoadStatus.Loading,
    val isRetryAvailable: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class StationsScreenUiState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val gasStation: GasStation = GasStation(id = 0, address = ""),
    val stations: List<Station> = listOf(),
    val isTakeAvailable: Boolean = true,
)