package com.benzo.benzomobile.presentation.screen.service

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.presentation.Destination
import com.benzo.benzomobile.presentation.screen.service.finish.finishScreen
import com.benzo.benzomobile.presentation.screen.service.fuel_selection.fuelSelectionScreen
import com.benzo.benzomobile.presentation.screen.service.loading.loadingScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.serviceGraphRoot(
    onNavigateToGasStationsScreen: () -> Unit,
) {
    composable<Destination.AppGraph.GasStationsGraph.ServiceGraphRoot> {
        val stationId = 1
        val stationIdString = "01"

        val navController = rememberNavController()

        val destinations = listOf(
            Destination.AppGraph.GasStationsGraph.ServiceGraph.LoadingScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen(stationId = stationId),
            Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen,
            Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
        )

        var currentDestinationIndex by rememberSaveable { mutableIntStateOf(0) }

        BackHandler(
            onBack = {
                navController.navigate(
                    Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
                ) {
                    popUpTo(destinations[currentDestinationIndex]) {
                        inclusive = true
                    }
                }

                currentDestinationIndex = 3
            }
        )

        val viewModel = koinViewModel<ServiceGraphViewModel> { parametersOf(stationIdString) }

        LaunchedEffect(true) {
            viewModel.loadState.collect {
                if (!it.isLoading) {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FuelSelectionScreen(
                            stationId = stationId
                        ),
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 1
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = Destination.AppGraph.GasStationsGraph.ServiceGraph.LoadingScreen,
        ) {
            loadingScreen()

            fuelSelectionScreen(
                onCancelRefueling = {
                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.FinishScreen,
                    ) {
                        popUpTo(destinations[currentDestinationIndex]) {
                            inclusive = true
                        }
                    }

                    currentDestinationIndex = 3
                },
                onNavigateNext = {
                    viewModel.saveFuelSelectionResult(it)

                    navController.navigate(
                        Destination.AppGraph.GasStationsGraph.ServiceGraph.PaymentSelectionScreen,
                    )

                    currentDestinationIndex = 1
                }
            )

//            TODO paymentScreen()

            finishScreen(
                onNavigateToGasStationsScreen = onNavigateToGasStationsScreen
            )
        }



//        val paymentSelectionScreenViewModel = koinViewModel<PaymentSelectionScreenViewModel>()
//        val paymentSelectionScreenLoadState = paymentSelectionScreenViewModel
//            .loadState.collectAsStateWithLifecycle()
//        val paymentSelectionScreenUiState = paymentSelectionScreenViewModel
//            .uiState.collectAsStateWithLifecycle()
//
//        PaymentSelectionScreen(
//            bonusesUsed = paymentSelectionScreenUiState.value.bonusesUsed,
//            onBonusesUsedChange = paymentSelectionScreenViewModel::onBonusesUsedChange,
//            bonusesAvailable = paymentSelectionScreenUiState.value.bonusesAvailable,
//            cardNumber = paymentSelectionScreenUiState.value.cardNumber,
//            onCardNumberChange = paymentSelectionScreenViewModel::onCardNumberChange,
//            cardNumberError = paymentSelectionScreenUiState.value.cardNumberError,
//            expirationDate = paymentSelectionScreenUiState.value.expirationDate,
//            onExpirationDateChange = paymentSelectionScreenViewModel::onExpirationDateChange,
//            expirationDateError = paymentSelectionScreenUiState.value.expirationDateError,
//            holderName = paymentSelectionScreenUiState.value.holderName,
//            onHolderNameChange = paymentSelectionScreenViewModel::onHolderNameChange,
//            holderNameError = paymentSelectionScreenUiState.value.holderNameError,
//            paymentAmount = paymentSelectionScreenUiState.value.paymentAmount,
//            isPayAvailable = paymentSelectionScreenUiState.value.isPayAvailable,
//            snackbarHostState = paymentSelectionScreenLoadState.value.snackbarHostState,
//            onBackClick = {
//
//            },
//            onCancelRefuelingClick = {
//                viewModel.onCancelRefuelingClick()
//            },
//            onPayClick = {
//                paymentSelectionScreenViewModel.onPayClick()
//            },
//        )

    }
}