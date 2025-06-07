package com.benzo.benzomobile.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.edit_profile.editProfileScreen
import com.benzo.benzomobile.presentation.screen.profile.profileScreen
import com.benzo.benzomobile.presentation.screen.settings.settingsScreen

fun NavGraphBuilder.profileGraphRoot(
    onNavigateToLoginGraphRoot: () -> Unit,
) {
    composable<Destination.AppGraph.ProfileGraphRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.ProfileGraph.ProfileScreen,
        ) {
            profileScreen(
                onNavigateToSettingsScreen = {
                    navController.navigate(Destination.AppGraph.ProfileGraph.SettingsScreen)
                },
                onNavigateToEditProfileScreen = {
                    navController.navigate(Destination.AppGraph.ProfileGraph.EditProfileScreen)
                },
                onNavigateToLoginGraphRoot = onNavigateToLoginGraphRoot,
            )

            settingsScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
            )

            editProfileScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}