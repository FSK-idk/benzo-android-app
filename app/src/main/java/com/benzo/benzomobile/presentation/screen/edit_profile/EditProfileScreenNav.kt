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
                phoneNumberValue = "",
                onPhoneNumberValueChange = {},
                lastNameValue = "",
                onLastNameValueChange = {},
                firstNameValue = uiState.value.firstName,
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
}