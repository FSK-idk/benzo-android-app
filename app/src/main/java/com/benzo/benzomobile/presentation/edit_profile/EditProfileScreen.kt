package com.benzo.benzomobile.presentation.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.util.Calendar

@Composable
fun EditProfileScreen(
    phoneNumberValue: String,
    onPhoneNumberValueChange: (String) -> Unit,
    lastNameValue: String,
    onLastNameValueChange: (String) -> Unit,
    firstNameValue: String,
    onFirstNameValueChange: (String) -> Unit,
    emailValue: String,
    onEmailValueChange: (String) -> Unit,
    birthDateValue: String,
    onBirthDateValueChange: (String) -> Unit,
    genderValue: String,
    onGenderValueChange: () -> Unit,
    showDatePicker: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

//        val context = LocalContext.current
//        DatePickerDialog(
//            context,
//            { _, selectedYear, selectedMonth, selectedDay ->
//                birthDate =
//                    String.format("%02d.%02d.%d", selectedDay, selectedMonth + 1, selectedYear)
//                showDatePicker = false
//            },
//            year,
//            month,
//            day,
//        ).show()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = onBackClick,
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                        imageVector = Icons.Default.ChevronLeft,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Edit profile",
                style = MaterialTheme.typography.titleLarge,
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                value = phoneNumberValue,
                onValueChange = onPhoneNumberValueChange,
                isError = false,
                label = {
                    Text(
                        text = "Phone number *"
                    )
                },
                supportingText = {},
                singleLine = true,
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                value = lastNameValue,
                onValueChange = onLastNameValueChange,
                isError = false,
                label = {
                    Text(
                        text = "Last name *"
                    )
                },
                supportingText = {},
                singleLine = true,
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                value = firstNameValue,
                onValueChange = onFirstNameValueChange,
                isError = false,
                label = {
                    Text(
                        text = "First name *"
                    )
                },
                supportingText = {},
                singleLine = true,
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                value = emailValue,
                onValueChange = onEmailValueChange,
                isError = false,
                label = {
                    Text(
                        text = "Email *"
                    )
                },
                supportingText = {},
                singleLine = true,
            )

//
//            UnderlinedTextFieldWithIcon(
//                value = birthDateValue,
//                onValueChange = onBirthDateValueChange,
//                label = "Дата рождения *",
//                icon = {
//                    IconButton(onClick = { showDatePicker = true }) {
//                        Icon(
//                            imageVector = Icons.Default.CalendarToday,
//                            contentDescription = "Выбрать дату"
//                        )
//                    }
//                },
//                readOnly = false
//            )

            Text(
                text = "Sex *",
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = genderValue == "Male",
                        onClick = onGenderValueChange,
                    )
                    Text("Male")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = genderValue == "Female",
                        onClick = onGenderValueChange,
                    )
                    Text("Female")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Save",
                )
            }
        }
    }
}

// Do we need it?
@Composable
fun UnderlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.001.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            singleLine = true
        )
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
    }
}

// Do we need it?
@Composable
fun UnderlinedTextFieldWithIcon(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.titleMedium)
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(bottom = 0.001.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            trailingIcon = icon,
            singleLine = true,
            readOnly = readOnly
        )
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}

@Composable
@Preview
fun EditProfileScreenPreview() {
    BenzoMobileTheme {
        Surface {
            EditProfileScreen(
                phoneNumberValue = "",
                onPhoneNumberValueChange = {},
                lastNameValue = "",
                onLastNameValueChange = {},
                firstNameValue = "",
                onFirstNameValueChange = {},
                emailValue = "",
                onEmailValueChange = {},
                birthDateValue = "",
                onBirthDateValueChange = {},
                genderValue = "",
                onGenderValueChange = {},
                showDatePicker = false,
                onBackClick = {},
                onSaveClick = {},
            )
        }
    }
}