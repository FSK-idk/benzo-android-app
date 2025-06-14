package com.benzo.benzomobile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benzo.benzomobile.domain.model.FilterQuery
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BzFilterBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    prefixId: String,
    onPrefixIdChange: (String) -> Unit,
    prefixAddress: String,
    onPrefixAddressChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSearch: (FilterQuery) -> Unit,
    isSearchAvailable: Boolean,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            BzOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Номер АЗС",
                value = prefixId,
                onValueChange = { onPrefixIdChange(it.filter { jt -> jt.isDigit() }) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )

            BzOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Адрес",
                value = prefixAddress,
                onValueChange = onPrefixAddressChange,
            )

            BzButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onSearch(
                        FilterQuery(
                            prefixId = prefixId,
                            prefixAddress = prefixAddress,
                        )
                    )
                },
                text = "Искать",
                isAvailable = isSearchAvailable,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BzFilterBottomSheetPreview() {
    BenzoMobileTheme {
        Surface {
            BzFilterBottomSheet(
                modifier = Modifier,
                sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded),
                prefixId = "",
                onPrefixIdChange = {},
                prefixAddress = "",
                onPrefixAddressChange = {},
                onDismiss = {},
                onSearch = {},
                isSearchAvailable = true,
            )
        }
    }
}