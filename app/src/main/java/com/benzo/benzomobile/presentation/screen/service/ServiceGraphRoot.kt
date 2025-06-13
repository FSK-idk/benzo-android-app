package com.benzo.benzomobile.presentation.screen.service

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.finish.finishScreen
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.fuelSelectionScreen
import com.benzo.benzomobile.presentation.screen.service.gas_nozzle_use.gasNozzleUseScreen
import com.benzo.benzomobile.presentation.screen.service.loading.loadingScreen
import com.benzo.benzomobile.presentation.screen.service.payment_selection.paymentSelectionScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.serviceGraphRoot(
    onNavigateToGasStationsScreen: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraphRoot> {
        val destination =
            it.toRoute<Destination.AppGraph.GasStationsGraph.ServiceGraphRoot>()

        val stationId = destination.stationId
        val stationIdString = destination.stationId.toString().padStart(2, '0')

        val navController = rememberNavController()

        val destinations = listOf(
            Destination.AppGraph.GasStationsGraph.ServiceGraph.LoadingScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.GasNozzleUseScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
        )

        var currentDestinationIndex by rememberSaveable { mutableIntStateOf(0) }

        val viewModel =
            koinViewModel<ServiceGraphViewModel> { parametersOf(stationId, stationIdString) }

        LaunchedEffect(true) {
            viewModel.loadState.collect {
                if (!it.isLoading) {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen,
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 1
                }
                if (it.isFinish) {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 4
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.GasStationsGraph.ServiceGraph.LoadingScreen,
        ) {
            loadingScreen()

            fuelSelectionScreen(
                viewModel = viewModel,
                onCancelRefueling = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 4
                },
                onNavigateNext = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen,
                    )

                    currentDestinationIndex = 1
                }
            )

            paymentSelectionScreen(
                viewModel = viewModel,
                onCancelRefueling = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 4
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateNext = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.GasNozzleUseScreen,
                    )

                    currentDestinationIndex = 3
                }
            )

            gasNozzleUseScreen()

            finishScreen(
                onNavigateToGasStationsScreen = onNavigateToGasStationsScreen
            )
        }
    }
}