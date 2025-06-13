package com.benzo.benzomobile.presentation.screen.profile

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.domain.model.User
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow(ProfileScreenLoadState())
    val loadState = _loadState.asStateFlow()

    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val user = getUserUseCase()

                _uiState.update {
                    it.copy(
                        login = user.login,
                        name = user.name ?: "",
                    )
                }

                _loadState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    fun onRefresh() {
        if (!_loadState.value.isRefreshing) {
            viewModelScope.launch {
                _loadState.update { it.copy(isRefreshing = true) }

                try {
                    val user = getUserUseCase()

                    _uiState.update {
                        it.copy(
                            login = user.login,
                            name = user.name ?: "",
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "$e")
                    _loadState.value.snackbarHostState.showSnackbar(
                        message = e.message ?: "Ошибка",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                }

                _loadState.update { it.copy(isRefreshing = false) }
            }
        }
    }

    fun onExitClick(navigateNext: () -> Unit) =
        viewModelScope.launch {
            try {
                logoutUseCase()
                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _loadState.value.snackbarHostState.showSnackbar(
                    message = e.message ?: "Ошибка",
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