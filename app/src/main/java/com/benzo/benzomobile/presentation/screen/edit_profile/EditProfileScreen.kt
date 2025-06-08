package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.presentation.common.CarNumberSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.DateSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.GenderSimpleOutlinedTextFiled
import com.benzo.benzomobile.presentation.common.PhoneNumberSimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.SimpleOutlinedTextField
import com.benzo.benzomobile.presentation.common.SimpleTopAppBar
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
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
    birthDate: String,
    onBirthDateChange: (String) -> Unit,
    birthDateError: String?,
    gender: GenderOption,
    onGenderChange: (GenderOption) -> Unit,
    genderError: String?,
    showDatePicker: Boolean,
    onShowDatePickerChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { onShowDatePickerChange(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            onBirthDateChange(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        }
                        onShowDatePickerChange(false)
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onShowDatePickerChange(false) }
                ) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                title = "Edit Profile",
                onBackClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            PhoneNumberSimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                phoneNumber = phoneNumber,
                onPhoneNumberChange = onPhoneNumberChange,
                phoneNumberError = phoneNumberError,
                title = "Phone number",
            )

            SimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = onNameChange,
                valueError = nameError,
                title = "Name",
            )

            SimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = onEmailChange,
                valueError = emailError,
                title = "Email",
            )

            DateSimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                date = birthDate,
                onDateClick = { onShowDatePickerChange(true) },
                dateError = birthDateError,
                title = "Birth date",
            )

            CarNumberSimpleOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                carNumber = carNumber,
                onCarNumberChange = onCarNumberChange,
                carNumberError = carNumberError,
                title = "Car number",
            )

            GenderSimpleOutlinedTextFiled(
                modifier = Modifier.fillMaxWidth(),
                gender = gender,
                onGenderChange = onGenderChange,
                genderError = genderError,
                title = "Gender",
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSaveClick
            ) {
                Text(text = "Save")
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
                phoneNumber = "",
                onPhoneNumberChange = {},
                name = "",
                onNameChange = {},
                email = "",
                onEmailChange = {},
                birthDate = "01.01.2000",
                onBirthDateChange = {},
                gender = GenderOption.NONE,
                onGenderChange = {},
                showDatePicker = false,
                onBackClick = {},
                onSaveClick = {},
                emailError = "",
                phoneNumberError = "",
                onShowDatePickerChange = {},
                nameError = "",
                carNumber = "",
                carNumberError = "",
                onCarNumberChange = {},
                birthDateError = "",
                genderError = ""
            )
        }
    }
}






