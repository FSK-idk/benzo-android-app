package com.benzo.benzomobile.presentation.edit_profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileScreenViewModel(

) : ViewModel() {
    // user info

    private val _firstNameValue = MutableStateFlow<String>("")
    val firstNameValue = _firstNameValue.asStateFlow()

    // ui events

    fun onFirstValueChange(value: String) {
        _firstNameValue.value = value
    }

    fun onSaveClick() {
        // TODO
    }
}