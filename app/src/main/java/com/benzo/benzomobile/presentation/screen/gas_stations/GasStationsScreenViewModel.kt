package com.benzo.benzomobile.presentation.screen.gas_stations

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.GasStation
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.use_case.FetchGasStationsUseCase
import com.benzo.benzomobile.domain.use_case.GetGasStationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GasStationsScreenViewModel(
    private val getGasStationsUseCase: GetGasStationsUseCase,
    private val fetchGasStationsUseCase: FetchGasStationsUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(GasStationsScreenLoadState())
    val loadState = _loadState.asStateFlow()

    val uiState =
        getGasStationsUseCase()
            .catch { e ->
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
            .filterIsInstance<Resource.Loaded<List<GasStation>>>()
            .map {
                _loadState.update { jt -> jt.copy(isLoading = false) }

                GasStationsScreenUiState(
                    gasStations = it.data
                )
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = GasStationsScreenUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        try {
            fetchGasStationsUseCase()
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
}

data class GasStationsScreenLoadState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class GasStationsScreenUiState(
    val gasStations: List<GasStation> = listOf(),
)