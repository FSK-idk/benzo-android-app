package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(EditProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onFirstValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                firstName = value,
            )
        }
    }

    fun onSaveClick() {
        // TODO
    }
}