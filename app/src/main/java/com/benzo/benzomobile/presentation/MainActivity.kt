package com.benzo.benzomobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.benzo.benzomobile.presentation.graph.GraphRoot
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BenzoMobileTheme {
                GraphRoot()
            }
        }
    }
}