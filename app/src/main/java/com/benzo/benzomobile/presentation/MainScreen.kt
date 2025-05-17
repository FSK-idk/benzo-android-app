package com.benzo.benzomobile.presentation

import LoyaltyCardScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benzo.benzomobile.nav.Destination
import com.benzo.benzomobile.presentation.example_second.ExampleSecondScreen
import com.benzo.benzomobile.presentation.profile.EditProfileScreen
import com.benzo.benzomobile.presentation.profile.ProfileScreenRoot
import com.benzo.benzomobile.presentation.profile.ThemeSelectionScreen

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.ProfileScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destination.ProfileScreen.route) {
                ProfileScreenRoot(navController)
            }
            composable(Destination.ExampleSecondScreen.route) {
                ExampleSecondScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("theme_screen") {
                var theme by rememberSaveable { mutableStateOf("Системная") }
                ThemeSelectionScreen(
                    selectedTheme = theme,
                    onThemeSelected = { theme = it },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("edit_profile_screen") {
                EditProfileScreen(navController = navController, onBackClick = { navController.popBackStack()})
            }
            composable("loyalty_card_screen") {
                LoyaltyCardScreen(navController = navController)
            }

        }
    }
}
