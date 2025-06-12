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
import com.benzo.benzomobile.domain.model.FuelType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelTypeSimpleOutlinedTextFiled(
    modifier: Modifier = Modifier,
    fuelTypes: List<FuelType>,
    selectedFuelTypeIndex: Int,
    onFuelTypeChange: (Int) -> Unit,
    fuelTypeError: String? = null,
    title: String,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            value = getFuelTypeName(fuelTypes[selectedFuelTypeIndex]),
            onValueChange = {},
            readOnly = true,
            isError = fuelTypeError != null,
            supportingText = {
                fuelTypeError?.let {
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
            fuelTypes.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = getFuelTypeName(item),
                            color = if (index == selectedFuelTypeIndex) MaterialTheme.colorScheme.primary else Color.Unspecified
                        )
                    },
                    onClick = {
                        onFuelTypeChange(index)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

private fun getFuelTypeName(fuelType: FuelType) =
    when (fuelType) {
        FuelType.PETROL_92 -> "92"
        FuelType.PETROL_95 -> "95"
        FuelType.PETROL_98 -> "98"
        FuelType.DIESEL -> "DT"
    }
