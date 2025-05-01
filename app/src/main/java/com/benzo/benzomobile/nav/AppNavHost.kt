package com.benzo.benzomobile.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benzo.benzomobile.presentation.example.ExampleScreenViewModel
import com.benzo.benzomobile.presentation.example.ExampleScreenRoot
import com.benzo.benzomobile.presentation.example_second.ExampleSecondScreenRoot
import com.benzo.benzomobile.presentation.example_second.ExampleSecondScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navHostController.navigate(
                action.destination,
            ) {
                action.navOptions(this)
            }

            is NavigationAction.NavigateUp -> navHostController.navigateUp()
        }
    }

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = navigator.startDestination,
    ) {
        composable<Destination.ExampleScreen> {
            val viewModel = koinViewModel<ExampleScreenViewModel>()
            ExampleScreenRoot(viewModel = viewModel)
        }

        composable<Destination.ExampleSecondScreen> {
            val viewModel = koinViewModel<ExampleSecondScreenViewModel>()
            ExampleSecondScreenRoot(viewModel = viewModel)
        }
    }
}