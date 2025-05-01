package com.benzo.benzomobile.presentation.example_second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.ExampleModel
import com.benzo.benzomobile.domain.use_case.GetExampleModelListUseCase
import com.benzo.benzomobile.nav.Navigator
import com.benzo.benzomobile.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExampleSecondScreenViewModel(
    private val navigator: Navigator,
    private val getExampleModelListUseCase: GetExampleModelListUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val loadState = _loadState.asStateFlow()

    private val _exampleModels = MutableStateFlow<List<ExampleModel>>(listOf())
    val exampleModels = _exampleModels.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading

                _exampleModels.value = getExampleModelListUseCase()

                _loadState.value = LoadState.NotLoading
            } catch (e: Exception) {
                _loadState.value = LoadState.Error
            }
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    fun onRetryClick() {
        loadData()
    }
}