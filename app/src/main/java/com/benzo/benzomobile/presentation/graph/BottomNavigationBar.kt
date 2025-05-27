package com.benzo.benzomobile.presentation.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.benzo.benzomobile.presentation.Destination

data class NavigationItem(
    val currentDestination: Destination.AppGraph,
    val title: String,
    val icon: ImageVector,
    val onClick: (Destination.AppGraph) -> Unit,
)

@Composable
fun BottomNavigationBar(
    onNavigateToProfileRoot: (Destination.AppGraph) -> Unit,
    onNavigateToStationsRoot: (Destination.AppGraph) -> Unit,
    onNavigateToLoyaltyCardRoot: (Destination.AppGraph) -> Unit,
) {
    val navigationItems = listOf(
        NavigationItem(
            currentDestination = Destination.AppGraph.ProfileGraphRoot,
            title = "Profile",
            icon = Icons.Default.Person,
            onClick = onNavigateToProfileRoot,
        ),
        NavigationItem(
            currentDestination = Destination.AppGraph.StationsGraphRoot,
            title = "Stations",
            icon = Icons.Default.LocalGasStation,
            onClick = onNavigateToStationsRoot,
        ),
        NavigationItem(
            currentDestination = Destination.AppGraph.LoyaltyCardGraphRoot,
            title = "Loyalty card",
            icon = Icons.Default.CardMembership,
            onClick = onNavigateToLoyaltyCardRoot,
        )
    )

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
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
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue) {
                            Color.Black
                        } else {
                            Color.Gray
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}