package com.benzo.benzomobile.presentation.screen.gas_stations

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.use_case.GetGasStationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GasStationsScreenViewModel(
    private val getGasStationsUseCase: GetGasStationsUseCase,
) : ViewModel() {
    private var _filterQuery = FilterQuery("", "")

    private val _loadState = MutableStateFlow(GasStationsScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(GasStationsScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
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
        val gasStations = getGasStationsUseCase(
            filterQuery = _filterQuery
        )

        _uiState.update { it.copy(gasStations = gasStations) }
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

    fun onPrefixIdChange(value: String) {
        _uiState.update { it.copy(prefixId = value) }
    }

    fun onPrefixAddressChange(value: String) {
        _uiState.update { it.copy(prefixAddress = value) }
    }

    fun onSearch(filterQuery: FilterQuery) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSearchAvailable = false) }

            try {
                _filterQuery = filterQuery

                loadData()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                sendMessage(message = e.message)
            }

            _uiState.update { it.copy(isSearchAvailable = true) }
        }
    }
}

data class GasStationsScreenLoadState(
    val loadStatus: LoadStatus = LoadStatus.Loading,
    val isRetryAvailable: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class GasStationsScreenUiState(
    val gasStations: List<GasStation> = listOf(),
    val prefixId: String = "",
    val prefixAddress: String = "",
    val isSearchAvailable: Boolean = true,
)