package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzButton(
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
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    ) {
        if (!isAvailable) {
            CircularProgressIndicator(modifier = Modifier.size(25.dp))
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
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
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BzButtonPreview() {
    BenzoMobileTheme {
        Surface {
            BzButton(
                modifier = Modifier.width(200.dp),
                onClick = {},
                text = "text",
                isAvailable = true,
            )
        }
    }
}