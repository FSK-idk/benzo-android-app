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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benzo.benzomobile.domain.model.GenderOption
import com.benzo.benzomobile.domain.model.getGenderName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzGenderOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: GenderOption,
    onValueChange: (GenderOption) -> Unit,
    valueError: String? = null,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val genders = listOf(
        GenderOption.MALE,
        GenderOption.FEMALE,
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            label = { Text(text = label) },
            value = getGenderName(value),
            onValueChange = {},
            singleLine = true,
            readOnly = true,
            isError = valueError != null,
            supportingText = { valueError?.let { Text(text = it) } },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ) {
            genders.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = getGenderName(it),
                            color =
                                if (it == value) MaterialTheme.colorScheme.primary
                                else Color.Unspecified,
                        )
                    },
                    onClick = {
                        expanded = false
                        onValueChange(it)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}