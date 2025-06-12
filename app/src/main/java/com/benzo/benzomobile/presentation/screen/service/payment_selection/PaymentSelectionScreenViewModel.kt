package com.benzo.benzomobile.presentation.screen.service.payment_selection

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.FuelSelectionResult
import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.use_case.FetchLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.PayUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCardNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateExpirationDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateHolderNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentSelectionScreenViewModel(
    private val validateCardNumberUseCase: ValidateCardNumberUseCase,
    private val validateExpirationDateUseCase: ValidateExpirationDateUseCase,
    private val validateHolderNameUseCase: ValidateHolderNameUseCase,
//    private val fetchLoyaltyCardUseCase: FetchLoyaltyCardUseCase,
//    private val getLoyaltyCardUseCase: GetLoyaltyCardUseCase,
    private val payUseCase: PayUseCase,
    private val fuelSelectionResult: FuelSelectionResult,
) : ViewModel() {
    private val _loadState = MutableStateFlow(PaymentSelectionScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(PaymentSelectionScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    bonusesAvailable = 102346,
                    paymentAmount = fuelSelectionResult.paymentAmount
                )
            }

            _loadState.update { it.copy(isLoading = false) }
        }
    }

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

    fun onPayClick() {
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
                paymentAmount = _uiState.value.paymentAmount - (
                        if (_uiState.value.bonusesUsed.isBlank()) 0
                        else (_uiState.value.bonusesUsed.toFloat() * 100.0f).toInt())
            )

            viewModelScope.launch {
                _uiState.update { it.copy(isPayAvailable = false) }

                try {
                    val response = payUseCase(request)
                    Log.d(TAG, "response = ${response.paymentKey}")
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


data class PaymentSelectionScreenLoadState(
    val isLoading: Boolean = true,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class PaymentSelectionScreenUiState(
    val bonusesUsed: String = "",
    val bonusesAvailable: Int = 0,
    val cardNumber: String = "",
    val cardNumberError: String? = null,
    val expirationDate: String = "",
    val expirationDateError: String? = null,
    val holderName: String = "",
    val holderNameError: String? = null,
    val paymentAmount: Int = 0,
    val isPayAvailable: Boolean = true,
)