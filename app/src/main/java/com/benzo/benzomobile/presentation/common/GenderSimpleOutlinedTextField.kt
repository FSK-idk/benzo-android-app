package com.benzo.benzomobile.presentation.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benzo.benzomobile.domain.model.GenderOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderSimpleOutlinedTextFiled(
    modifier: Modifier = Modifier,
    gender: GenderOption,
    onGenderChange: (GenderOption) -> Unit,
    genderError: String? = null,
    title: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val genders = listOf(GenderOption.MALE, GenderOption.FEMALE)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            value = getGenderName(gender),
            onValueChange = {},
            readOnly = true,
            isError = genderError != null,
            supportingText = {
                genderError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            label = { Text(text = title) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            genders.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = getGenderName(it),
                            color = if (it == gender) MaterialTheme.colorScheme.primary else Color.Unspecified
                        )
                    },
                    onClick = {
                        onGenderChange(it)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

private fun getGenderName(gender: GenderOption) =
    when (gender) {
        GenderOption.NONE -> ""
        GenderOption.MALE -> "Мужской"
        GenderOption.FEMALE -> "Женский"
    }