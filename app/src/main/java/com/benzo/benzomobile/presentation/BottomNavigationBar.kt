package com.benzo.benzomobile.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.benzo.benzomobile.nav.Destination

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val bottomBarScreens = listOf(
        Destination.ProfileScreen.route,
        "loyalty_card_screen",
        Destination.ExampleSecondScreen.route
    )

    if (currentRoute in bottomBarScreens) {
        NavigationBar {
            NavigationBarItem(
                selected = currentRoute == Destination.ProfileScreen.route,
                onClick = { navController.navigate(Destination.ProfileScreen.route) },
                icon = { Icon(Icons.Default.Person, contentDescription = "Профиль") },
                label = { Text("Профиль") }
            )
            NavigationBarItem(
                selected = currentRoute == "loyalty_card_screen",
                onClick = { navController.navigate("loyalty_card_screen") },
                icon = { Icon(Icons.Default.Article, contentDescription = "Карта") },
                label = { Text("Карта") }
            )
            NavigationBarItem(
                selected = currentRoute == Destination.ExampleSecondScreen.route,
                onClick = { navController.navigate(Destination.ExampleSecondScreen.route) },
                icon = { Icon(Icons.Default.Percent, contentDescription = "Статистика") },
                label = { Text("test") }
            )
        }
    }
}

