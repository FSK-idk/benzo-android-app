package com.benzo.benzomobile.presentation.screen.loyalty_card

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.model.LoyaltyCard
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.use_case.FetchLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoyaltyCardScreenViewModel(
    private val getLoyaltyCardUseCase: GetLoyaltyCardUseCase,
    private val fetchLoyaltyCardUseCase: FetchLoyaltyCardUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(LoyaltyCardScreenLoadState())
    val loadState = _loadState.asStateFlow()

    val uiState = combine(
        getLoyaltyCardUseCase().filter {
            when (it) {
                is Result.Loading -> false
                is Result.Success -> true

                is Result.Error -> {
                    _loadState.value.snackbarHostState.showSnackbar(
                        message = it.message,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                    return@filter false
                }
            }
        }
            .filterIsInstance<Result.Success<LoyaltyCard>>(),
        getUserUseCase().filter {
            when (it) {
                is Result.Loading -> false
                is Result.Success -> true

                is Result.Error -> {
                    _loadState.value.snackbarHostState.showSnackbar(
                        message = it.message,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                    return@filter false
                }
            }
        }
            .filterIsInstance<Result.Success<User>>(),
    ) { loyaltyCard, user ->
        _loadState.update { jt -> jt.copy(isLoading = false) }

        LoyaltyCardScreenUiState(
            loyaltyCard = loyaltyCard.data,
            login = user.data.login,
        )
    }
        .stateIn(
            scope = viewModelScope,
            initialValue = LoyaltyCardScreenUiState(),
            started = SharingStarted.WhileSubscribed(5000),
        )


    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    fun onRefresh() {
        if (!_loadState.value.isRefreshing) {
            _loadState.update { it.copy(isRefreshing = true) }
            viewModelScope.launch {
                fetchData()
                _loadState.update { it.copy(isRefreshing = false) }
            }
        }
    }

    private suspend fun fetchData() {
        fetchUserUseCase()
        fetchLoyaltyCardUseCase()
    }
}

data class LoyaltyCardScreenLoadState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class LoyaltyCardScreenUiState(
    val loyaltyCard: LoyaltyCard = LoyaltyCard(
        number = "",
        balance = 0,
    ),
    val login: String = "",
)