package com.benzo.benzomobile.presentation.screen.payment_history

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Payment
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.use_case.FetchPaymentHistoryUseCase
import com.benzo.benzomobile.domain.use_case.GetPaymentHistoryCardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentHistoryViewModel(
    private val getPaymentHistoryCardUseCase: GetPaymentHistoryCardUseCase,
    private val fetchPaymentHistoryUseCase: FetchPaymentHistoryUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(PaymentHistoryScreenLoadState())
    val loadState = _loadState.asStateFlow()

    val uiState =
        getPaymentHistoryCardUseCase()
            .catch { e ->
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
            .filterIsInstance<Resource.Loaded<List<Payment>>>()
            .map {
                _loadState.update { jt -> jt.copy(isLoading = false) }

                PaymentHistoryScreenUiState(
                    paymentHistory = it.data
                )
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = PaymentHistoryScreenUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        try {
            fetchPaymentHistoryUseCase()
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

data class PaymentHistoryScreenLoadState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class PaymentHistoryScreenUiState(
    val paymentHistory: List<Payment> = listOf(),
)
