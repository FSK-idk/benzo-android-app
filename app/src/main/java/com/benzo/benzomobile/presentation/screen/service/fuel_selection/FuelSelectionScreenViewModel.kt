package com.benzo.benzomobile.presentation.screen.service.fuel_selection

import androidx.lifecycle.ViewModel
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.use_case.ValidateFuelAmountUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePaymentAmountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FuelSelectionScreenViewModel(
    private val validateFuelAmountUseCase: ValidateFuelAmountUseCase,
    private val validatePaymentAmountUseCase: ValidatePaymentAmountUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FuelSelectionScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                fuels = listOf(
                    Fuel(FuelType.PETROL_92, 3254),
                    Fuel(FuelType.DIESEL, 4524),
                ),
                selectedFuelIndex = 0,
            )
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

    fun onContinueClick(onNavigateNext: () -> Unit) {
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
            onNavigateNext()
        }
    }
}

data class FuelSelectionScreenUiState(
    val fuels: List<Fuel> = listOf(),
    val selectedFuelIndex: Int = 0,
    val fuelAmount: String = "",
    val fuelAmountError: String? = null,
    val paymentAmount: String = "",
    val paymentAmountError: String? = null,
)