package com.benzo.benzomobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BenzoMobileTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController)
            }
        }
    }
}