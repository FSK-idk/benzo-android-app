package com.benzo.benzomobile.presentation.screen.payment_history

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.use_case.GetPaymentHistoryCardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentHistoryViewModel(
    private val getPaymentHistoryCardUseCase: GetPaymentHistoryCardUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(PaymentHistoryScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(PaymentHistoryScreenUiState())
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
        val paymentHistory = getPaymentHistoryCardUseCase()

        _uiState.update { it.copy(paymentHistory = paymentHistory) }
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
}

data class PaymentHistoryScreenLoadState(
    val loadStatus: LoadStatus = LoadStatus.Loading,
    val isRetryAvailable: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class PaymentHistoryScreenUiState(
    val paymentHistory: List<Payment> = listOf(),
)