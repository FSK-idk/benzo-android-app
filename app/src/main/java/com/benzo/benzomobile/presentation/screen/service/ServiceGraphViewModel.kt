package com.benzo.benzomobile.presentation.screen.service

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ServiceGraphViewModel(
    private val stationId: String,
) : ViewModel() {
    private val _loadState = MutableStateFlow(ServiceLoadState())
    val loadState = _loadState.asStateFlow()

    private var _fuelSelectionResult: FuelSelectionResult? = null

    private val _client: WebSocketClient = WebSocketClient(
        stationId = stationId,
        onServiceEnd = this::onServiceEnd,
        onServiceStart = this::onServiceStart,
    )

    init {
        _client.start()
    }

    override fun onCleared() {
        _client.stop()
        super.onCleared()
    }

    private fun onServiceStart() {
        Log.d(TAG, "SERVICE STARTED")
        _loadState.update { it.copy(isLoading = false) }
    }

    private fun onServiceEnd() {
        Log.d(TAG, "SERVICE ENDED")
    }

    fun saveFuelSelectionResult(fuelSelectionResult: FuelSelectionResult) {
        _fuelSelectionResult = fuelSelectionResult
    }
}

data class ServiceLoadState(
    val isLoading: Boolean = true,
)