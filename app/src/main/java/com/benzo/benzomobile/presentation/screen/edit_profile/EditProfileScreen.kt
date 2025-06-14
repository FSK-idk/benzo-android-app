package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.domain.model.LoadStatus
import com.benzo.benzomobile.presentation.common.BzButton
import com.benzo.benzomobile.presentation.common.BzCarNumberOutlinedTextFiled
import com.benzo.benzomobile.presentation.common.BzDateOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzErrorBox
import com.benzo.benzomobile.presentation.common.BzGenderOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzLoadingBox
import com.benzo.benzomobile.presentation.common.BzOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzPhoneNumberOutlinedTextField
import com.benzo.benzomobile.presentation.common.BzPullToRefreshBox
import com.benzo.benzomobile.presentation.common.BzTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus,
    onRetry: () -> Unit,
    isRetryAvailable: Boolean,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    snackbarHostState: SnackbarHostState,
    name: String,
    onNameChange: (String) -> Unit,
    nameError: String?,
    carNumber: String,
    onCarNumberChange: (String) -> Unit,
    carNumberError: String?,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    phoneNumberError: String?,
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: String?,
    birthDate: LocalDate?,
    onBirthDateChange: (LocalDate) -> Unit,
    birthDateError: String?,
    gender: GenderOption,
    onGenderChange: (GenderOption) -> Unit,
    genderError: String?,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    isSaveAvailable: Boolean,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BzTopAppBar(
                title = "Редактирование профиля",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        when (loadStatus) {
            is LoadStatus.Loading -> {
                BzLoadingBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadStatus.Error -> {
                BzErrorBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    message = loadStatus.message,
                    onRetry = onRetry,
                    isRetryAvailable = isRetryAvailable,
                )
            }

            is LoadStatus.Loaded -> {
                BzPullToRefreshBox(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    onRefresh = onRefresh,
                    isRefreshing = isRefreshing,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        BzOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Имя",
                            value = name,
                            onValueChange = onNameChange,
                            valueError = nameError,
                        )

                        BzCarNumberOutlinedTextFiled(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Номер машины",
                            value = carNumber,
                            onValueChange = onCarNumberChange,
                            valueError = carNumberError,
                        )

                        BzPhoneNumberOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Номер телефона",
                            value = phoneNumber,
                            onValueChange = onPhoneNumberChange,
                            valueError = phoneNumberError,
                        )

                        BzOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Почта",
                            value = email,
                            onValueChange = onEmailChange,
                            valueError = emailError,
                        )

                        BzDateOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Дата рождения",
                            value = birthDate,
                            onValueChange = onBirthDateChange,
                            valueError = birthDateError,
                        )

                        BzGenderOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Пол",
                            value = gender,
                            onValueChange = onGenderChange,
                            valueError = genderError,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        BzButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onSaveClick,
                            text = "Сохранить",
                            isAvailable = isSaveAvailable,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            EditProfileScreen(
                modifier = Modifier,
                loadStatus = LoadStatus.Loaded,
                onRetry = {},
                isRetryAvailable = true,
                onRefresh = {},
                isRefreshing = false,
                snackbarHostState = SnackbarHostState(),
                name = "",
                onNameChange = {},
                nameError = "",
                carNumber = "",
                carNumberError = "",
                onCarNumberChange = {},
                phoneNumber = "",
                onPhoneNumberChange = {},
                phoneNumberError = "",
                email = "",
                onEmailChange = {},
                emailError = "",
                birthDate = null,
                onBirthDateChange = {},
                birthDateError = "",
                gender = GenderOption.NONE,
                onGenderChange = {},
                genderError = "",
                onBackClick = {},
                onSaveClick = {},
                isSaveAvailable = true,
            )
        }
    }
}






