package com.yoesuv.kmplanguage

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yoesuv.kmplanguage.core.route.AppRoute
import com.yoesuv.kmplanguage.feature.home.HomeScreen
import com.yoesuv.kmplanguage.feature.settings.SettingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = AppRoute.Home
            ) {
                composable<AppRoute.Home> {
                    HomeScreen(navController)
                }

                composable<AppRoute.Settings> {
                    SettingScreen()
                }
            }
        }
    }
}