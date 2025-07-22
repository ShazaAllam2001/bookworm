package com.example.bookworm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.modules.bookGrid.ui.components.BookDetails
import com.example.bookworm.modules.myLibrary.ui.components.BookList
import com.example.bookworm.modules.explore.ui.Explore
import com.example.bookworm.modules.foryou.ui.ForYou
import com.example.bookworm.modules.myLibrary.ui.MyLibrary
import com.example.bookworm.modules.settings.ui.Settings

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    onChangeBottomBarState: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ForYou.route
    ) {
        composable(route = BottomBarScreen.ForYou.route) {
            onChangeBottomBarState(true)
            ForYou(navController = navController)
        }
        composable(route = BottomBarScreen.Explore.route) {
            onChangeBottomBarState(true)
            Explore(navController = navController)
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            onChangeBottomBarState(true)
            MyLibrary(navController = navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            onChangeBottomBarState(true)
            Settings()
        }
        composable(
            route = "books/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.IntType
            })
        ) {
            onChangeBottomBarState(false)
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
            onChangeBottomBarState(true)
            val libraryId = it.arguments?.getInt("libraryId")?:0
            BookList(
                navController = navController,
                libraryId  = libraryId
            )
        }
    }
}