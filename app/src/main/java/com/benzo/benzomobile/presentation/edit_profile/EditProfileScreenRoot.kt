package com.benzo.benzomobile.presentation.edit_profile

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun EditProfileScreenRoot(
    viewModel: EditProfileScreenViewModel,
    onNavigateBack: () -> Unit,
) {
    val firstNameValue = viewModel.firstNameValue.collectAsState()

    Scaffold { innerPadding ->
        EditProfileScreen(
            phoneNumberValue = "",
            onPhoneNumberValueChange = {},
            lastNameValue = "",
            onLastNameValueChange = {},
            firstNameValue = firstNameValue.value,
            onFirstNameValueChange = viewModel::onFirstValueChange,
            emailValue = "",
            onEmailValueChange = {},
            birthDateValue = "",
            onBirthDateValueChange = {},
            genderValue = "",
            onGenderValueChange = {},
            showDatePicker = false,
            onBackClick = onNavigateBack,
            onSaveClick = viewModel::onSaveClick,
        )
    }

}