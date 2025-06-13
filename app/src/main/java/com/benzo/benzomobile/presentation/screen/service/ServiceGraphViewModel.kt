package com.benzo.benzomobile.presentation.screen.service

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Fuel
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.MobileAppSavePaymentMessage
import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetStationFuelsUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.PayUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCardNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateExpirationDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateFuelAmountUseCase
import com.benzo.benzomobile.domain.use_case.ValidateHolderNameUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePaymentAmountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServiceGraphViewModel(
    private val stationIdInt: Int,
    private val stationIdStr: String,
    private val getStationFuelsUseCase: GetStationFuelsUseCase,
    private val getLoyaltyCardUseCase: GetLoyaltyCardUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val payUseCase: PayUseCase,
    private val validateFuelAmountUseCase: ValidateFuelAmountUseCase,
    private val validatePaymentAmountUseCase: ValidatePaymentAmountUseCase,
    private val validateCardNumberUseCase: ValidateCardNumberUseCase,
    private val validateExpirationDateUseCase: ValidateExpirationDateUseCase,
    private val validateHolderNameUseCase: ValidateHolderNameUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(ServiceLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(ServiceUiState())
    val uiState = _uiState.asStateFlow()

    private val _client: WebSocketClient = WebSocketClient(
        stationId = stationIdStr,
        onServiceEnd = this::onServiceEnd,
        onServiceStart = this::onServiceStart,
        onMobileAppUsed = this::onMobileAppUsed,
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

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(fuels = getStationFuelsUseCase(stationIdInt)) }
                _uiState.update { it.copy(bonusesAvailable = getLoyaltyCardUseCase().balance) }

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

    private fun onServiceEnd() {
        Log.d(TAG, "SERVICE ENDED")
    }

    private fun onMobileAppUsed() {
        _loadState.update { it.copy(isFinish = true) }
    }

//    FUEL

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

//    PAYMENT


    fun onBonusesUsedChange(value: String) {
        _uiState.update { it.copy(bonusesUsed = value) }
    }

    fun onCardNumberChange(value: String) {
        _uiState.update {
            it.copy(
                cardNumber = value,
                cardNumberError = validateCardNumberUseCase(value),
            )
        }
    }

    fun onExpirationDateChange(value: String) {
        _uiState.update {
            it.copy(
                expirationDate = value,
                expirationDateError = validateExpirationDateUseCase(value),
            )
        }
    }

    fun onHolderNameChange(value: String) {
        _uiState.update {
            it.copy(
                holderName = value,
                holderNameError = validateHolderNameUseCase(value),
            )
        }
    }

    fun onPayClick(onNavigateNext: () -> Unit) {
        val cardNumberError =
            validateCardNumberUseCase(_uiState.value.cardNumber)
        val expirationDateError =
            validateExpirationDateUseCase(_uiState.value.expirationDate)
        val holderNameError =
            validateHolderNameUseCase(_uiState.value.holderName)

        val hasErrors = listOf(
            cardNumberError,
            expirationDateError,
            holderNameError,
        ).any { it != null }

        _uiState.update {
            it.copy(
                cardNumberError = cardNumberError,
                expirationDateError = expirationDateError,
                holderNameError = holderNameError,
            )
        }

        if (!hasErrors) {
            val request = PayRequest(
                depositCardNumber = _uiState.value.cardNumber,
                depositCardExpirationDate = _uiState.value.expirationDate.let {
                    "20" + it.substring(2, 4) + "-" + it.substring(0, 2) + "-01"
                },
                depositCardHolderName = _uiState.value.holderName,
                paymentAmount = (_uiState.value.paymentAmount.toFloat() * 100.0f).toInt() - (
                        if (_uiState.value.bonusesUsed.isBlank()) 0
                        else (_uiState.value.bonusesUsed.toFloat() * 100.0f).toInt())
            )

            viewModelScope.launch {
                _uiState.update { it.copy(isPayAvailable = false) }

                try {
                    val response = payUseCase(request)
                    Log.d(TAG, "response = ${response.paymentKey}")

                    _client.savePayment(
                        message = MobileAppSavePaymentMessage(
                            fuelType = when (_uiState.value.fuels[_uiState.value.selectedFuelIndex].type) {
                                FuelType.PETROL_92 -> "92"
                                FuelType.PETROL_95 -> "95"
                                FuelType.PETROL_98 -> "98"
                                FuelType.DIESEL -> "DT"
                            },
                            fuelAmount = (_uiState.value.fuelAmount.toFloat() * 100.0f).toInt(),
                            carNumber = getUserUseCase().carNumber!!,
                            paymentAmount = (_uiState.value.paymentAmount.toFloat() * 100.0f).toInt() - (
                                    if (_uiState.value.bonusesUsed.isBlank()) 0
                                    else (_uiState.value.bonusesUsed.toFloat() * 100.0f).toInt()),
                            paymentKey = response.paymentKey,
                            usedBonuses = if (_uiState.value.bonusesUsed.isBlank()) 0
                            else (_uiState.value.bonusesUsed.toFloat() * 100.0f).toInt(),
                        )
                    )

                    _client.useGasNozzle()

                    onNavigateNext()
                } catch (e: Exception) {
                    Log.e(TAG, "$e")
                    _loadState.value.snackbarHostState.showSnackbar(
                        message = e.message ?: "Ошибка",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                }

                _uiState.update { it.copy(isPayAvailable = true) }
            }
        }
    }
}

data class ServiceLoadState(
    val isLoading: Boolean = true,
    val isFinish: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class ServiceUiState(
//    FUEL
    val fuels: List<Fuel> = listOf(),
    val selectedFuelIndex: Int = 0,
    val fuelAmount: String = "",
    val fuelAmountError: String? = null,
    val paymentAmount: String = "",
    val paymentAmountError: String? = null,
//    PAYMENT
    val bonusesUsed: String = "",
    val bonusesAvailable: Int = 0,
    val cardNumber: String = "",
    val cardNumberError: String? = null,
    val expirationDate: String = "",
    val expirationDateError: String? = null,
    val holderName: String = "",
    val holderNameError: String? = null,
    val isPayAvailable: Boolean = true,
)