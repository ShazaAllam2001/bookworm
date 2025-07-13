package com.example.bookworm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookworm.screens.Explore
import com.example.bookworm.screens.ForYou
import com.example.bookworm.screens.MyLibrary
import com.example.bookworm.screens.Settings

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ForYou.route
    ) {
        composable(route = BottomBarScreen.ForYou.route) {
            ForYou()
        }
        composable(route = BottomBarScreen.Explore.route) {
            Explore()
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            MyLibrary()
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings()
        }
    }
}