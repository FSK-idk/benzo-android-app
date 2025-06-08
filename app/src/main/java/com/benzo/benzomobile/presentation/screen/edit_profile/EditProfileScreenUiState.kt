package com.benzo.benzomobile.presentation.screen.edit_profile

import com.benzo.benzomobile.domain.model.GenderOption

data class EditProfileScreenUiState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val birthDate: String = "",
    val birthDateError: String? = null,
    val gender: GenderOption = GenderOption.NONE,
    val genderError: String? = null,
    val showDatePicker: Boolean = false,
    val carNumber: String = "",
    val carNumberError: String? = null
)