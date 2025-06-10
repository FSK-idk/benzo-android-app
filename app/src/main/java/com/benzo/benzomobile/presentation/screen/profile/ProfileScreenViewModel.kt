package com.benzo.benzomobile.presentation.screen.profile

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(ProfileScreenLoadState())
    val loadState = _loadState.asStateFlow()

    val uiState = getUserUseCase()
        .filter {
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
        .filterIsInstance<Result.Success<User>>()
        .map {
            _loadState.update { jt -> jt.copy(isLoading = false) }

            ProfileScreenUiState(
                login = it.data.login,
                name = it.data.name,
            )
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = ProfileScreenUiState(),
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

    private suspend fun fetchData() =
        fetchUserUseCase()

    fun onExitClick(navigateNext: () -> Unit) =
        viewModelScope.launch {
            try {
                logoutUseCase()
                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = "Ошибка сети",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
}

data class ProfileScreenLoadState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
)

data class ProfileScreenUiState(
    val name: String = "",
    val login: String = "",
)