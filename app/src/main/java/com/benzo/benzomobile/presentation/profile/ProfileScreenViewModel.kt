package com.benzo.benzomobile.presentation.profile

import androidx.lifecycle.ViewModel
import com.benzo.benzomobile.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate

class ProfileScreenViewModel(

) : ViewModel() {
    // user info

    private val _icon = MutableStateFlow<Int>(R.drawable.ic_launcher_foreground)
    val icon = _icon.asStateFlow()

    private val _name = MutableStateFlow<String>("")
    val name = _name.asStateFlow()

    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _email = MutableStateFlow<String>("")
    val email = _email.asStateFlow()

    private val _registrationDate = MutableStateFlow<LocalDate>(LocalDate.fromEpochDays(0))
    val registrationDate = _registrationDate.asStateFlow()

    // ui events

    fun onExitClick() {
        // TODO
    }
}