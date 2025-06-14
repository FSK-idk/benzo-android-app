package com.benzo.benzomobile.presentation.common

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

@Composable
fun BzNavigationBar(
    onNavigateToProfileRoot: (Destination.AppGraph) -> Unit,
    onNavigateToStationsRoot: (Destination.AppGraph) -> Unit,
    onNavigateToLoyaltyCardRoot: (Destination.AppGraph) -> Unit,
) {
    val navigationItems = listOf(
        NavigationItem(
            currentDestination = Destination.AppGraph.ProfileGraphRoot,
            label = "Профиль",
            icon = Icons.Default.Person,
            onClick = onNavigateToProfileRoot,
        ),
        NavigationItem(
            currentDestination = Destination.AppGraph.GasStationsGraphRoot,
            label = "АЗС",
            icon = Icons.Default.LocalGasStation,
            onClick = onNavigateToStationsRoot,
        ),
        NavigationItem(
            currentDestination = Destination.AppGraph.LoyaltyCardGraphRoot,
            label = "Карта",
            icon = Icons.Default.CardMembership,
            onClick = onNavigateToLoyaltyCardRoot,
        ),
    )

    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    item.onClick(navigationItems[selectedNavigationIndex.intValue].currentDestination)
                    selectedNavigationIndex.intValue = index
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(text = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    unselectedIconColor = MaterialTheme.colorScheme.surface,
                    selectedTextColor = MaterialTheme.colorScheme.surface,
                    unselectedTextColor = MaterialTheme.colorScheme.surface,
                )
            )
        }
    }
}

data class NavigationItem(
    val currentDestination: Destination.AppGraph,
    val label: String,
    val icon: ImageVector,
    val onClick: (Destination.AppGraph) -> Unit,
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BzNavigationBarPreview() {
    BenzoMobileTheme {
        Surface {
            BzNavigationBar(
                onNavigateToProfileRoot = {},
                onNavigateToStationsRoot = {},
                onNavigateToLoyaltyCardRoot = {},
            )
        }
    }
}