package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    suffix: String? = null,
    valueError: String? = null,
    isPassword: Boolean = false,
    onVisibilityClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        suffix = suffix?.let { { Text(text = it) } },
        singleLine = true,
        isError = valueError != null,
        supportingText = { valueError?.let { Text(text = it) } },
        visualTransformation =
            if (isPassword) PasswordVisualTransformation()
            else VisualTransformation.None,
        trailingIcon = onVisibilityClick?.let {
            {
                IconButton(
                    onClick = onVisibilityClick,
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector =
                            if (isPassword) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                        contentDescription = null,
                    )
                }
            }
        },
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BzOutlinedTextFieldPreview() {
    BenzoMobileTheme {
        Surface {
            BzOutlinedTextField(
                modifier = Modifier,
                label = "label",
                value = "value",
                onValueChange = {},
                valueError = "error",
                isPassword = false,
                onVisibilityClick = {},
            )
        }
    }
}