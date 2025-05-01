package com.benzo.benzomobile.presentation.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benzo.benzomobile.domain.model.ExampleModel
import com.benzo.benzomobile.domain.use_case.GetExampleModelListUseCase
import com.benzo.benzomobile.nav.Destination
import com.benzo.benzomobile.nav.Navigator
import com.benzo.benzomobile.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ExampleScreenViewModel(
    private val navigator: Navigator,
    private val getExampleModelListUseCase: GetExampleModelListUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val loadState = _loadState.asStateFlow()

    private val _exampleModels = MutableStateFlow<List<ExampleModel>>(listOf())
    val exampleModels = _exampleModels.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

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

    fun onButtonClick() {
        _text.value = Random.nextInt(0, 10).toString()
    }

    fun onNextClick() {
        viewModelScope.launch {
            navigator.navigate(Destination.ExampleSecondScreen)
        }
    }

    fun onRetryClick() {
        loadData()
    }
}