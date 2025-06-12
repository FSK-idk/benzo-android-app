package com.benzo.benzomobile.presentation.screen.service

import androidx.lifecycle.ViewModel
import com.benzo.benzomobile.domain.use_case.ValidateFuelAmountUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePaymentAmountUseCase

class ServiceViewModel(
    private val validateFuelAmountUseCase: ValidateFuelAmountUseCase,
    private val validatePaymentAmountUseCase: ValidatePaymentAmountUseCase,
) : ViewModel() {

    init {

    }


    fun onCancelRefuelingClick() {

    }

}


