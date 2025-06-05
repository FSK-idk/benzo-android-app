package com.benzo.benzomobile.presentation.screen.edit_profile

data class EditProfileScreenUiState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val birthDate: String = "",
    val birthDateError: String? = null,
    val gender: String = "",
    val genderError: String? = null,
    val showDatePicker: Boolean = false,
    val carNumber: String = "",
    val carNumberError: String? = null
)