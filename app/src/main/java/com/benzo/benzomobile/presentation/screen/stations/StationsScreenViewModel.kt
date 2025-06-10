package com.benzo.benzomobile.presentation.screen.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StationsScreenUiState(
    val gasStation: GasStation = GasStation(0, ""),
    val stations: List<Station> = listOf(),
)

class StationsScreenViewModel(

) : ViewModel() {
    private val _uiState = MutableStateFlow(StationsScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchGasStations()
    }

    private fun fetchGasStations() {
        viewModelScope.launch {
            // todo: fetch data
        }
    }
}