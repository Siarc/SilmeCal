package com.taman.silmebagcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taman.silmebagcalculator.ui.screens.dashboard.DashboardScreen
import com.taman.silmebagcalculator.ui.screens.login.LoginScreen
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenScreen
import com.taman.silmebagcalculator.ui.theme.SilmeBagCalculatorTheme
import com.taman.silmebagcalculator.utils.LoginScreen
import com.taman.silmebagcalculator.utils.DashboardScreen
import com.taman.silmebagcalculator.utils.NonWovenScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SilmeBagCalculatorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen
                ) {
                    composable<LoginScreen> {
                        LoginScreen(navController = navController)
                    }
                    composable<DashboardScreen> {
                        DashboardScreen(navController = navController)
                    }
                    composable<NonWovenScreen> {
                        NonWovenScreen(navController = navController)
                    }
                }
            }
        }
    }
}







