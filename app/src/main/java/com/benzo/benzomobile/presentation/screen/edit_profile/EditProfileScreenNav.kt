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
                phoneNumber = uiState.value.phoneNumber,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
                name = uiState.value.name,
                onNameChange = viewModel::onNameChange,
                email = uiState.value.email,
                onEmailChange = viewModel::onEmailChange,
                birthDate = uiState.value.birthDate,
                onBirthDateChange = viewModel::onBirthDateChange,
                gender = uiState.value.gender,
                onGenderChange = viewModel::onGenderChange,
                onBackClick = onNavigateBack,
                onSaveClick = viewModel::onSaveClick,
                emailError = uiState.value.emailError,
                phoneNumberError = uiState.value.phoneNumberError,
                showDatePicker = uiState.value.showDatePicker,
                onShowDatePickerChange = viewModel::setShowDatePicker,
                nameError = uiState.value.nameError,
                carNumber = uiState.value.carNumber,
                onCarNumberChange = viewModel::onCarNumberChange,
                carNumberError = uiState.value.carNumberError,
                birthDateError = uiState.value.birthDateError,
                genderError = uiState.value.genderError
            )
        }
    }
}