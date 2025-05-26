package com.benzo.benzomobile.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.benzo.benzomobile.nav.AppNavHost
import com.benzo.benzomobile.ui.theme.BenzoMobileTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var activityIntent = Intent(this, LoginActivity::class.java)
//        startActivity(activityIntent)
//        finish()
//
        setContent {
            BenzoMobileTheme {
                AppNavHost()
//                MainScreen(navController = navController)
            }
        }
    }
}