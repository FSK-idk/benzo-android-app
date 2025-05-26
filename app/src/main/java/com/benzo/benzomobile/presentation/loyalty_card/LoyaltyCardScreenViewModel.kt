package com.benzo.benzomobile.presentation.loyalty_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoyaltyCardScreenViewModel(

) : ViewModel() {
    // card info

    private val _cardNumber = MutableStateFlow<String>("")
    val cardNumber = _cardNumber.asStateFlow()

    private val _bonusesCount = MutableStateFlow<Int>(0)
    val bonusesCount = _bonusesCount.asStateFlow()

    private val _name = MutableStateFlow<String>("")
    val name = _name.asStateFlow()

    private val _year = MutableStateFlow<Int>(0)
    val year = _year.asStateFlow()
}