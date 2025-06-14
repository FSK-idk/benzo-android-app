package com.benzo.benzomobile.presentation.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.domain.model.FuelType
import com.benzo.benzomobile.domain.model.getFuelTypeName
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzFuelTypeOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    values: List<FuelType>,
    index: Int,
    onIndexChange: (Int) -> Unit,
    valueError: String? = null,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            label = { Text(text = label) },
            value = getFuelTypeName(values[index]),
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
            values.forEachIndexed { idx, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = getFuelTypeName(item),
                            color =
                                if (index == idx) MaterialTheme.colorScheme.primary
                                else Color.Unspecified,
                        )
                    },
                    onClick = {
                        expanded = false
                        onIndexChange(idx)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Preview
@Composable
private fun BzFuelTypeOutlinedTextFieldPreview() {
    BenzoMobileTheme {
        Surface {
            BzFuelTypeOutlinedTextField(
                modifier = Modifier,
                label = "label",
                values = listOf(FuelType.PETROL_95, FuelType.DIESEL),
                index = 0,
                onIndexChange = {},
                valueError = "error",
            )
        }
    }
}