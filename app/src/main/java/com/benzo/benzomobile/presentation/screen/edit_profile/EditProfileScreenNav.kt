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
        val loadState = viewModel.loadState.collectAsStateWithLifecycle()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        EditProfileScreen(
            isLoading = loadState.value.isLoading,
            name = uiState.value.name,
            onNameChange = viewModel::onNameChange,
            nameError = uiState.value.nameError,
            carNumber = uiState.value.carNumber,
            onCarNumberChange = viewModel::onCarNumberChange,
            carNumberError = uiState.value.carNumberError,
            phoneNumber = uiState.value.phoneNumber,
            onPhoneNumberChange = viewModel::onPhoneNumberChange,
            phoneNumberError = uiState.value.phoneNumberError,
            email = uiState.value.email,
            onEmailChange = viewModel::onEmailChange,
            emailError = uiState.value.emailError,
            birthDate = uiState.value.birthDate,
            onBirthDateChange = viewModel::onBirthDateChange,
            birthDateError = uiState.value.birthDateError,
            gender = uiState.value.gender,
            onGenderChange = viewModel::onGenderChange,
            genderError = uiState.value.genderError,
            showDatePicker = uiState.value.showDatePicker,
            onShowDatePickerChange = viewModel::setShowDatePicker,
            snackbarHostState = loadState.value.snackbarHostState,
            isSaveAvailable = loadState.value.isSaveAvailable,
            onBackClick = onNavigateBack,
            onSaveClick = viewModel::onSaveClick,
        )
    }
}