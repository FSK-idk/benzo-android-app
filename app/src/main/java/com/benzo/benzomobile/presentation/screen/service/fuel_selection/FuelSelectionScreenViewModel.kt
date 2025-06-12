package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.use_case.GetStationFuelsUseCase
import com.benzo.benzomobile.domain.use_case.ValidateFuelAmountUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePaymentAmountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FuelSelectionScreenViewModel(
    private val stationId: Int,
    private val getStationFuelsUseCase: GetStationFuelsUseCase,
    private val validateFuelAmountUseCase: ValidateFuelAmountUseCase,
    private val validatePaymentAmountUseCase: ValidatePaymentAmountUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(FuelSelectionScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(FuelSelectionScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(fuels = getStationFuelsUseCase(stationId)) }
                _loadState.update { it.copy(isLoading = false) }
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

    fun onFuelTypeChange(index: Int) {
        _uiState.update { it.copy(selectedFuelIndex = index) }
        onFuelAmountChange(_uiState.value.fuelAmount)
    }

    fun onFuelAmountChange(value: String) {
        _uiState.update {
            it.copy(
                fuelAmount = value,
                fuelAmountError = null,
                paymentAmountError = null,
            )
        }

        if (value.isBlank()) {
            _uiState.update { it.copy(paymentAmount = "") }
            return
        }

        val selectedFuel = _uiState.value.fuels[
            _uiState.value.selectedFuelIndex]
        val fuelAmount = value.toFloat()
        val paymentAmount = fuelAmount * (selectedFuel.price / 100.0f)

        _uiState.update { it.copy(paymentAmount = "%.2f".format(paymentAmount)) }
    }

    fun onPaymentAmountChange(value: String) {
        _uiState.update {
            it.copy(
                paymentAmount = value,
                fuelAmountError = null,
                paymentAmountError = null,
            )
        }

        if (value.isBlank()) {
            _uiState.update { it.copy(fuelAmount = "") }
            return
        }

        val selectedFuel = _uiState.value.fuels[
            _uiState.value.selectedFuelIndex]
        val paymentAmount = value.toFloat()
        val fuelAmount = paymentAmount / (selectedFuel.price / 100.0f)

        _uiState.update { it.copy(fuelAmount = "%.2f".format(fuelAmount)) }
    }

    fun onContinueClick(onNavigateNext: (FuelSelectionResult) -> Unit) {
        val fuelAmountError =
            validateFuelAmountUseCase(_uiState.value.fuelAmount)
        val paymentAmountError =
            validatePaymentAmountUseCase(_uiState.value.paymentAmount)

        val hasErrors = listOf(
            fuelAmountError,
            paymentAmountError,
        ).any { it != null }

        _uiState.update {
            it.copy(
                fuelAmountError = fuelAmountError,
                paymentAmountError = paymentAmountError,
            )
        }

        if (!hasErrors) {
            val selectedFuel = _uiState.value.fuels[
                _uiState.value.selectedFuelIndex]

            onNavigateNext(
                FuelSelectionResult(
                    fuelType = selectedFuel.type,
                    fuelAmount = (_uiState.value.fuelAmount.toFloat() * 100.0f).toInt(),
                    paymentAmount = (_uiState.value.paymentAmount.toFloat() * 100.0f).toInt(),
                )
            )
        }
    }
}

data class FuelSelectionScreenLoadState(
    val isLoading: Boolean = true,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class FuelSelectionScreenUiState(
    val fuels: List<Fuel> = listOf(),
    val selectedFuelIndex: Int = 0,
    val fuelAmount: String = "",
    val fuelAmountError: String? = null,
    val paymentAmount: String = "",
    val paymentAmountError: String? = null,
)