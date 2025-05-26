package com.benzo.benzomobile.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.nav.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterScreenViewModel(

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

    // confirm password

    private val _confirmPasswordValue = MutableStateFlow<String>("")
    val confirmPasswordValue = _confirmPasswordValue.asStateFlow()

    private val _isConfirmPasswordValidationError = MutableStateFlow<Boolean>(false)
    val isConfirmPasswordValidationError = _isConfirmPasswordValidationError.asStateFlow()

    private val _confirmPasswordValidationError = MutableStateFlow<String>("")
    val confirmPasswordValidationError = _confirmPasswordValidationError.asStateFlow()

    // ui events

    fun onLoginValueChange(value: String) {
        _loginValue.value = value
    }

    fun onPasswordValueChange(value: String) {
        _passwordValue.value = value
    }

    fun onConfirmPasswordValueChange(value: String) {
        _confirmPasswordValue.value = value
    }

    // debug

    override fun onCleared() {
        super.onCleared()
        Log.d("DBG", "CLEAR REGISTER SCREEN")
    }
}