package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.Destination
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.editProfileScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraph.EditProfileScreen> {
        val viewModel = koinViewModel<EditProfileScreenViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            EditProfileScreen(
                modifier = Modifier.padding(innerPadding),
                phoneNumberValue = uiState.value.phoneNumber,
                onPhoneNumberValueChange = viewModel::onPhoneNumberChange,
                lastNameValue = uiState.value.lastName,
                onLastNameValueChange = viewModel::onLastNameChange,
                firstNameValue = uiState.value.firstName,
                onFirstNameValueChange = viewModel::onFirstNameChange,
                emailValue = uiState.value.email,
                onEmailValueChange = viewModel::onEmailChange,
                birthDateValue = uiState.value.birthDate,
                onBirthDateValueChange = viewModel::onBirthDateChange,
                genderValue = uiState.value.gender,
                onGenderValueChange = viewModel::onGenderChange,
                onBackClick = onNavigateBack,
                onSaveClick = viewModel::onSaveClick,
                emailError = uiState.value.emailError,
                phoneNumberError = uiState.value.phoneNumberError,
                showDatePicker = uiState.value.showDatePicker,
                onShowDatePickerChange = viewModel::setShowDatePicker,
                firstNameError = uiState.value.firstNameError,
                lastNameError = uiState.value.lastNameError,
                carNumberValue = uiState.value.carNumber,
                onCarNumberValueChange = viewModel::onCarNumberChange,
                carNumberError = uiState.value.carNumberError,
                birthDateError = uiState.value.birthDateError,
                genderError = uiState.value.genderError
            )
        }
    }
}