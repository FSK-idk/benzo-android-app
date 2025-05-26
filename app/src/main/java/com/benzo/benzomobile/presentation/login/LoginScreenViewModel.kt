package com.benzo.benzomobile.presentation.login

import androidx.compose.ui.unit.Density
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.nav.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(

) : ViewModel() {
    // login

    private val _loginValue = MutableStateFlow<String>("")
    val loginValue = _loginValue.asStateFlow()

    private val _isLoginValidationError = MutableStateFlow<Boolean>(false)
    val isLoginValidationError = _isLoginValidationError.asStateFlow()

    private val _loginValidationError = MutableStateFlow<String>("")
    val loginValidationError = _loginValidationError.asStateFlow()

    // password

    private val _passwordValue = MutableStateFlow<String>("")
    val passwordValue = _passwordValue.asStateFlow()

    private val _isPasswordValidationError = MutableStateFlow<Boolean>(false)
    val isPasswordValidationError = _isPasswordValidationError.asStateFlow()

    private val _passwordValidationError = MutableStateFlow<String>("")
    val passwordValidationError = _passwordValidationError.asStateFlow()

    private val _isPasswordShown = MutableStateFlow<Boolean>(false)
    val isPasswordShown = _isPasswordShown.asStateFlow()

    // ui events

    fun onLoginValueChange(value: String) {
        _loginValue.value = value
    }

    fun onPasswordValueChange(value: String) {
        _passwordValue.value = value
    }

    fun onPasswordVisibilityClick() {
        _isPasswordShown.value = !_isPasswordShown.value
    }
}