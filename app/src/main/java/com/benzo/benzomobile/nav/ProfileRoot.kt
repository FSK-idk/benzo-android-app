package com.benzo.benzomobile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.edit_profile.EditProfileScreenRoot
import com.benzo.benzomobile.presentation.edit_profile.EditProfileScreenViewModel
import com.benzo.benzomobile.presentation.profile.ProfileScreenRoot
import com.benzo.benzomobile.presentation.profile.ProfileScreenViewModel
import com.benzo.benzomobile.presentation.settings.SettingsScreenRoot
import com.benzo.benzomobile.presentation.settings.SettingsScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.profileRoot() {
    composable<Destination.ProfileRoot> {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.ProfileScreen,
        ) {
            profileScreen(
                onNavigateToSettingsScreen = {
                    navController.navigate(Destination.SettingsScreen)
                },
                onNavigateToEditProfileScreen = {
                    navController.navigate(Destination.EditProfileScreen)
                },
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

fun NavGraphBuilder.profileScreen(
    onNavigateToSettingsScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
) {
    composable<Destination.ProfileScreen> {
        val viewModel = koinViewModel<ProfileScreenViewModel>()
        // TODO: add state?
        ProfileScreenRoot(
            viewModel = viewModel,
            onNavigateToSettingsScreen = onNavigateToSettingsScreen,
            onNavigateToEditProfileScreen = onNavigateToEditProfileScreen,
        )
    }
}

fun NavGraphBuilder.settingsScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Destination.SettingsScreen> {
        val viewModel = koinViewModel<SettingsScreenViewModel>()
        // TODO: add state?
        SettingsScreenRoot(
            viewModel = viewModel,
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavGraphBuilder.editProfileScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Destination.EditProfileScreen> {
        val viewModel = koinViewModel<EditProfileScreenViewModel>()
        // TODO: add state?
        EditProfileScreenRoot(
            viewModel = viewModel,
            onNavigateBack = onNavigateBack,
        )
    }
}