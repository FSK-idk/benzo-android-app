package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(EditProfileScreenUiState())
    val uiState = _uiState.asStateFlow()
    companion object {
        private const val CAR_NUMBER_REGEX = """^(([АВЕКМНОРСТУХ]\d{3}(?<!000)[АВЕКМНОРСТУХ]{1,2})(\d{2,3})|(\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2})(\d{2})|(\d{3}(?<!000)(C?D|[ТНМВКЕ])\d{3}(?<!000))(\d{2}(?<!00))|([ТСК][АВЕКМНОРСТУХ]{2}\d{3}(?<!000))(\d{2})|([АВЕКМНОРСТУХ]{2}\d{3}(?<!000)[АВЕКМНОРСТУХ])(\d{2})|([АВЕКМНОРСТУХ]\d{4}(?<!0000))(\d{2})|(\d{3}(?<!000)[АВЕКМНОРСТУХ])(\d{2})|(\d{4}(?<!0000)[АВЕКМНОРСТУХ])(\d{2})|([АВЕКМНОРСТУХ]{2}\d{4}(?<!0000))(\d{2})|([АВЕКМНОРСТУХ]{2}\d{3}(?<!000))(\d{2,3})|(^Т[АВЕКМНОРСТУХ]{2}\d{3}(?<!000)\d{2,3}))$"""
    }


    fun onPhoneNumberChange(value: String) {
        val digitsOnly = value.filter { it.isDigit() }
        val error = validatePhoneNumber(digitsOnly)

        _uiState.update {
            it.copy(phoneNumber = digitsOnly, phoneNumberError = error)
        }
    }

    fun onLastNameChange(value: String) {
        val error = validateName(value)
        _uiState.update {
            it.copy(lastName = value, lastNameError = error)
        }
    }

    fun onFirstNameChange(value: String) {
        val error = validateName(value)
        _uiState.update {
            it.copy(firstName = value, firstNameError = error)
        }
    }

    fun onEmailChange(value: String) {
        val error = validateEmail(value)
        _uiState.update {
            it.copy(email = value, emailError = error)
        }
    }

    fun onBirthDateChange(value: String) {
        val error = validateBirthDate(value)
        _uiState.update {
            it.copy(birthDate = value, birthDateError = error)
        }
    }

    fun onGenderChange(value: String) {
        val error = validateGender(value)
        _uiState.update {
            it.copy(gender = value, genderError = error)
        }
    }
    fun onCarNumberChange(value: String) {

        val error = validateCarNumber(value)
        _uiState.update {
            it.copy(carNumber = value, carNumberError = error)
        }
    }
    fun setShowDatePicker(show: Boolean) {
        _uiState.update { it.copy(showDatePicker = show) }
    }

    private fun validatePhoneNumber(phone: String): String? {
        if (phone.isBlank()) return "The field cannot be empty"
        val digitsOnly = phone.filter { it.isDigit() }
        val isValidChars = phone.all { it.isDigit() || it in "+-() " }

        return when {
            !isValidChars -> "Invalid characters"
            digitsOnly.length < 10 -> "Incorrect number"
            else -> null
        }
    }

    private fun validateEmail(email: String): String? {
        if (email.isBlank()) return "The field cannot be empty"
        return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Incorrect email" else null
    }

    private fun validateBirthDate(birthDate: String): String? {
        return if (birthDate.isBlank()) "Select the date of birth" else null
    }

    private fun validateGender(gender: String): String? {
        return if (gender.isBlank()) "Choose a gender" else null
    }

    private fun validateName(name: String): String? {
        val regex = Regex("^[a-zA-Zа-яА-ЯёЁ\\-\\s']+$")
        return when {
            name.isBlank() -> "The field cannot be empty"
            !regex.matches(name) -> "Only letters, spaces, hyphens, and apostrophes are allowed."
            else -> null
        }
    }


    private fun validateCarNumber(carNumber: String): String? {
        if (carNumber.isBlank()) return "The field cannot be empty"
        return if (!Regex(CAR_NUMBER_REGEX).matches(carNumber.uppercase())) {
            "Incorrect car number"
        } else null
    }

    fun onSaveClick() {
        val firstNameError = validateName(_uiState.value.firstName)
        val lastNameError = validateName(_uiState.value.lastName)
        val phoneNumberError = validatePhoneNumber(_uiState.value.phoneNumber)
        val emailError = validateEmail(_uiState.value.email)
        val birthDateError = validateBirthDate(_uiState.value.birthDate)
        val genderError = validateGender(_uiState.value.gender)
        val carNumberError = validateCarNumber(_uiState.value.carNumber)

        val hasErrors = listOf(
            firstNameError,
            lastNameError,
            phoneNumberError,
            emailError,
            birthDateError,
            genderError,
            carNumberError
        ).any { it != null }

        _uiState.update {
            it.copy(
                firstNameError = firstNameError,
                lastNameError = lastNameError,
                phoneNumberError = phoneNumberError,
                emailError = emailError,
                birthDateError = birthDateError,
                genderError = genderError,
                carNumberError = carNumberError
            )
        }

        if (!hasErrors) {
            // TODO:
        }
    }
}