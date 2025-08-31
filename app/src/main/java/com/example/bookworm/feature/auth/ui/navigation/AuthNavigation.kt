package com.example.bookworm.feature.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookworm.common.constants.Screens
import com.example.bookworm.feature.splash.ui.SplashScreen
import com.example.bookworm.feature.auth.ui.login.LoginScreen


@Composable
fun AuthNavigation(
    navController: NavHostController,
    onNavigateToBrowser: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screens.Login.route)
                }
            )
        }

        composable(Screens.Login.route) {
            LoginScreen(
                onNavigateToBrowser = onNavigateToBrowser
            )
        }
    }
}