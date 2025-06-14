package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isAvailable: Boolean = true,
    isStyled: Boolean = false,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isAvailable,
        border = BorderStroke(
            width = 1.dp,
            color =
                if (isAvailable) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surfaceVariant
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    ) {
        if (!isAvailable) {
            CircularProgressIndicator(modifier = Modifier.size(25.dp))
        } else {
            Text(text = text)

            if (isStyled) {
                Spacer(modifier = Modifier.weight(1.0f))

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BzOutlinedButtonPreview() {
    BenzoMobileTheme {
        Surface {
            BzOutlinedButton(
                modifier = Modifier.width(200.dp),
                onClick = {},
                text = "text",
                isAvailable = false,
            )
        }
    }
}