package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DateSimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    date: String,
    onDateClick: () -> Unit,
    dateError: String? = null,
    title: String,
) {
    OutlinedTextField(
        modifier = modifier.clickable { onDateClick() },
        value = date,
        onValueChange = {},
        label = { Text(text = title) },
        singleLine = true,
        isError = dateError != null,
        supportingText = {
            dateError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        readOnly = true,
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable { onDateClick() },
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
            )
        }
    )
}