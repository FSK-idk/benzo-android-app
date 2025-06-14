package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzRadioOption(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isSelected: Boolean,
) {
    Row(
        modifier = modifier
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)

        Spacer(modifier = Modifier.weight(1f))

        RadioButton(
            selected = isSelected,
            onClick = null
        )
    }
}

@Preview
@Composable
private fun BzRadioOptionPreview() {
    BenzoMobileTheme {
        Surface {
            BzRadioOption(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "text",
                isSelected = true,
            )
        }
    }
}