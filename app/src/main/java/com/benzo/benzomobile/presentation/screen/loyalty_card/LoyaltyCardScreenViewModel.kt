package com.benzo.benzomobile.presentation.screen.loyalty_card

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoyaltyCardScreenViewModel(
    private val getLoyaltyCardUseCase: GetLoyaltyCardUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(LoyaltyCardScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(LoyaltyCardScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                loadData()

                _loadState.update { it.copy(loadStatus = LoadStatus.Loaded) }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    private suspend fun loadData() {
        val user = getUserUseCase()
        val loyaltyCard = getLoyaltyCardUseCase()

        _uiState.update {
            it.copy(
                loyaltyCard = loyaltyCard,
                login = user.login,
            )
        }
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

data class LoyaltyCardScreenLoadState(
    val loadStatus: LoadStatus = LoadStatus.Loading,
    val isRetryAvailable: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class LoyaltyCardScreenUiState(
    val loyaltyCard: LoyaltyCard = LoyaltyCard(
        number = "",
        balance = 0,
    ),
    val login: String = "",
)