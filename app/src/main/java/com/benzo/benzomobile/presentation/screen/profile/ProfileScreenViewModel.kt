package com.benzo.benzomobile.presentation.screen.profile

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val logoutUserUseCase: LogoutUserUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            try {
                val user = fetchUserUseCase()

                _uiState.update { currentState ->
                    currentState.copy(
                        name = user.login,
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _uiState.value.snackbarHostState.showSnackbar(
                    message = "Network error",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    fun onExitClick(navigateNext: () -> Unit) {
        viewModelScope.launch {
            try {
                logoutUserUseCase()
                navigateNext()
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                _uiState.value.snackbarHostState.showSnackbar(
                    message = "Network error",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }
}