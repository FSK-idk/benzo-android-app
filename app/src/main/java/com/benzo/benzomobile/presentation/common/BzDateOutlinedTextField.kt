package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char

@Composable
fun BzDateOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: LocalDate?,
    onValueChange: (LocalDate) -> Unit,
    valueError: String? = null,
) {
    var isDatePickerShown by rememberSaveable { mutableStateOf(false) }

    if (isDatePickerShown) {
        BzDatePickerDialog(
            date = value,
            onDismiss = { isDatePickerShown = false },
            onConfirm = {
                isDatePickerShown = false
                onValueChange(it)
            }
        )
    }

    OutlinedTextField(
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            isDatePickerShown = true
                        }
                    }
                }
            },
        label = { Text(text = label) },
        value = value?.format(
            LocalDate.Format {
                dayOfMonth()
                char('.')
                monthNumber()
                char('.')
                year()
            }
        ) ?: "",
        onValueChange = {},
        singleLine = true,
        readOnly = true,
        isError = valueError != null,
        supportingText = { valueError?.let { Text(text = it) } },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable { isDatePickerShown = true },
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BzDateOutlinedTextFieldPreview() {
    BenzoMobileTheme {
        Surface {
            BzDateOutlinedTextField(
                modifier = Modifier,
                label = "label",
                value = LocalDate.fromEpochDays(0),
                onValueChange = {},
                valueError = "error",
            )
        }
    }
}