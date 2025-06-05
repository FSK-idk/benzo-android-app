package com.benzo.benzomobile.presentation.screen.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
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
    onGenderValueChange: (String) -> Unit,
    showDatePicker: Boolean,
    onShowDatePickerChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    emailError: String?,
    phoneNumberError: String?,
    lastNameError: String?,
    firstNameError: String?,
    carNumberValue: String,
    onCarNumberValueChange: (String) -> Unit,
    carNumberError: String? = null,
    birthDateError: String?,
    genderError: String?
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
                            onBirthDateValueChange(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        }
                        onShowDatePickerChange(false)
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onShowDatePickerChange(false) }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                ) {
                    Icon(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary),
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Edit Profile",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = phoneNumberValue,
                onValueChange = onPhoneNumberValueChange,
                label = { Text("Phone number") },
                singleLine = true,
                visualTransformation = PhoneVisualTransformation(mask = "+7 (###) ###-##-##",
                    maskNumber = '#'),
                isError = phoneNumberError != null,
                supportingText = {
                    phoneNumberError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)

            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastNameValue,
                onValueChange = onLastNameValueChange,
                label = { Text("Last name") },
                singleLine = true,
                isError = lastNameError != null,
                supportingText = {
                    lastNameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstNameValue,
                onValueChange = onFirstNameValueChange,
                label = { Text("First name") },
                singleLine = true,
                isError = firstNameError != null,
                supportingText = {
                    firstNameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailValue,
                onValueChange = onEmailValueChange,
                label = { Text("Email") },
                singleLine = true,
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) {
                        Text(text = emailError, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onShowDatePickerChange(true) },
                value = birthDateValue,
                onValueChange = {},
                label = { Text("Birth date") },
                singleLine = true,
                isError = birthDateError != null,
                supportingText = {
                    birthDateError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { onShowDatePickerChange(true) },
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Select date"
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = carNumberValue,
                onValueChange = { input ->
                    val filtered = input
                        .uppercase()
                        .filter { it.isDigit() || it in 'А'..'Я' }
                        .take(8)

                    onCarNumberValueChange(filtered)
                },
                label = { Text("Car number") },
                singleLine = true,
                isError = carNumberError != null,
                supportingText = {
                    carNumberError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )

            var expanded by remember { mutableStateOf(false) }
            val genderOptions = listOf("Male", "Female")

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    value = genderValue,
                    onValueChange = {},
                    readOnly = true,
                    isError = genderError != null,
                    supportingText = {
                        genderError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                    },
                    label = { Text("Select gender") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genderOptions.forEach { gender ->
                        DropdownMenuItem(
                            text = { Text(text = gender, color = if (gender == genderValue) MaterialTheme.colorScheme.primary else Color.Unspecified) },
                            onClick = {
                                onGenderValueChange(gender)
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
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
                phoneNumberValue = "",
                onPhoneNumberValueChange = {},
                lastNameValue = "",
                onLastNameValueChange = {},
                firstNameValue = "",
                onFirstNameValueChange = {},
                emailValue = "",
                onEmailValueChange = {},
                birthDateValue = "01.01.2000",
                onBirthDateValueChange = {},
                genderValue = "Male",
                onGenderValueChange = {},
                showDatePicker = false,
                onBackClick = {},
                onSaveClick = {},
                emailError = "",
                phoneNumberError = "",
                onShowDatePickerChange = {},
                firstNameError = "",
                lastNameError = "",
                carNumberValue = "",
                carNumberError = "",
                onCarNumberValueChange = {},
                birthDateError = "",
                genderError = ""
            )
        }
    }
}


class PhoneVisualTransformation(
    private val mask: String,
    private val maskNumber: Char
) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(maxLength)

        val maskedText = buildAnnotatedString {
            var textIndex = 0
            for (maskChar in mask) {
                if (maskChar == maskNumber) {
                    if (textIndex < trimmed.length) {
                        append(trimmed[textIndex])
                        textIndex++
                    } else {
                        break
                    }
                } else {
                    append(maskChar)
                }
            }
        }

        return TransformedText(maskedText, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        return other is PhoneVisualTransformation &&
                other.mask == mask &&
                other.maskNumber == maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode() * 31 + maskNumber.hashCode()
    }
}


private class PhoneOffsetMapper(
    val mask: String,
    val numberChar: Char
) : OffsetMapping {

    private val firstInputPosition = mask.indexOf(numberChar)

    override fun originalToTransformed(offset: Int): Int {
        var digitsSeen = 0
        var transformedOffset = 0

        while (digitsSeen < offset && transformedOffset < mask.length) {
            if (mask[transformedOffset] == numberChar) {
                digitsSeen++
            }
            transformedOffset++
        }

        while (transformedOffset < mask.length && mask[transformedOffset] != numberChar) {
            transformedOffset++
        }

        return transformedOffset.coerceAtLeast(firstInputPosition)
    }

    override fun transformedToOriginal(offset: Int): Int {
        val digits = mask.take(offset).count { it == numberChar }
        return digits
    }
}





