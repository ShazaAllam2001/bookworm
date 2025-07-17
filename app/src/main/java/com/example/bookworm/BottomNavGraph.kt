package com.example.bookworm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.screens.BookDetails
import com.example.bookworm.screens.BookList
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
            ForYou(navController = navController)
        }
        composable(route = BottomBarScreen.Explore.route) {
            Explore()
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            MyLibrary(navController = navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings()
        }
        composable(
            route = "books/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.IntType
            })
        ) {
            val bookId = it.arguments?.getInt("bookId")?:0
            BookDetails(
                navController = navController,
                bookId = bookId
            )
        }
        composable(
            route = "librarys/{libraryId}",
            arguments = listOf(navArgument("libraryId"){
                type = NavType.IntType
            })
        ) {
            val libraryId = it.arguments?.getInt("libraryId")?:0
            BookList(
                navController = navController,
                libraryId  = libraryId
            )
        }
    }
}