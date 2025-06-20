package com.benzo.benzomobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.benzo.benzomobile.presentation.graph.GraphRoot
import com.benzo.benzomobile.presentation.util.isSystemInDarkTheme
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var themeSettings by mutableStateOf(
            ThemeSettings(
                darkTheme = resources.configuration.isSystemInDarkTheme,
            ),
        )

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    isSystemInDarkTheme(),
                    viewModel.uiState,
                ) { systemDark, uiState ->
                    ThemeSettings(
                        darkTheme = uiState.shouldUseDarkTheme(systemDark),
                    )
                }
                    .onEach { themeSettings = it }
                    .map { it.darkTheme }
                    .collect {}
            }
        }

        splashScreen.setKeepOnScreenCondition { viewModel.uiState.value.shouldKeepSplashScreen() }

        setContent {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()

            BenzoMobileTheme(
                darkTheme = themeSettings.darkTheme
            ) {
                if (uiState.value is MainActivityUiState.Success) {
                    GraphRoot(
                        isAuthenticated = (uiState.value as MainActivityUiState.Success).isAuthenticated
                    )
                }
            }
        }
    }
}

private data class ThemeSettings(
    val darkTheme: Boolean,
)
