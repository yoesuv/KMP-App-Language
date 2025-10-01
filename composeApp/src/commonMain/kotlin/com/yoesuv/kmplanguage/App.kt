package com.yoesuv.kmplanguage

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yoesuv.kmplanguage.components.AppTopBar
import com.yoesuv.kmplanguage.core.route.AppRoute
import com.yoesuv.kmplanguage.core.theme.AppColors
import com.yoesuv.kmplanguage.feature.home.HomeScreen
import com.yoesuv.kmplanguage.feature.settings.SettingScreen
import kmpapplanguage.composeapp.generated.resources.Res
import kmpapplanguage.composeapp.generated.resources.app_name
import kmpapplanguage.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource


@Composable
fun App() {
    // Initialize with saved language or default
    var lang by remember { mutableStateOf(getSavedLanguage()) }

    // Single handler to update both compose-resources and the platform default
    val onLanguageSelected: (String) -> Unit = { code ->
        lang = code
        changeLanguage(code)
    }

    LocalizedApp(lang) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = AppColors.Primary
            )
        ) {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            val title = when (currentRoute) {
                AppRoute.Home::class.qualifiedName -> stringResource(Res.string.app_name)
                AppRoute.Settings::class.qualifiedName -> stringResource(Res.string.settings)
                else -> stringResource(Res.string.app_name)
            }

            val canBack = currentRoute != AppRoute.Home::class.qualifiedName

            Scaffold(
                topBar = {
                    AppTopBar(
                        title = title,
                        canBack = canBack,
                        navigateUp = { navController.popBackStack() })
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = AppRoute.Home
                ) {
                    composable<AppRoute.Home> {
                        HomeScreen(navController, innerPadding)
                    }

                    composable<AppRoute.Settings> {
                        SettingScreen(innerPadding, onLanguageSelected)
                    }
                }
            }
        }
    }
}