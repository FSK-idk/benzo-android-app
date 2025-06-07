package com.benzo.benzomobile.presentation.screen.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// todo: rename to gas stations
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