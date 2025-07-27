package com.example.bookworm

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.bookGrid.data.bookListAR
import com.example.bookworm.modules.bookGrid.ui.components.BookDetails
import com.example.bookworm.modules.myLibrary.ui.components.BookList
import com.example.bookworm.modules.explore.ui.Explore
import com.example.bookworm.modules.foryou.ui.ForYou
import com.example.bookworm.modules.myLibrary.ui.MyLibrary
import com.example.bookworm.modules.settings.ui.Settings


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
            Explore(navController = navController)
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
            var book = bookList[bookId]
            if (LocalConfiguration.current.locales[0].language == "ar")
                book = bookListAR[bookId]
            BookDetails(
                navController = navController,
                book = book
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