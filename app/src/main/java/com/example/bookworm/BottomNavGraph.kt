package com.example.bookworm

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.modules.account.ui.LogIn
import com.example.bookworm.modules.account.ui.SignUp
import com.example.bookworm.modules.bookGrid.ui.components.BookDetails
import com.example.bookworm.modules.myLibrary.ui.components.BookList
import com.example.bookworm.modules.explore.ui.Explore
import com.example.bookworm.modules.foryou.ui.ForYou
import com.example.bookworm.modules.myLibrary.ui.MyLibrary
import com.example.bookworm.modules.settings.ui.Settings
import com.example.bookworm.modules.viewModel.BookModel
import com.example.bookworm.modules.viewModel.LibraryModel


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(route = Screens.Login.route) {
            LogIn(navController = navController)
        }
        composable(route = Screens.Signup.route) {
            SignUp(navController = navController)
        }
        composable(route = BottomBarScreen.ForYou.route) {
            val viewModel = BookModel(appLocale = LocalConfiguration.current.locales[0])
            ForYou(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Explore.route) {
            val viewModel = BookModel(appLocale = LocalConfiguration.current.locales[0])
            Explore(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            val viewModel = LibraryModel(appLocale = LocalConfiguration.current.locales[0])
            MyLibrary(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings()
        }
        composable(
            route = "books/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.StringType
            })
        ) {
            val bookId = it.arguments?.getString("bookId")?:""
            val viewModel = BookModel(appLocale = LocalConfiguration.current.locales[0])
            viewModel.searchBookById(bookId)

            BookDetails(
                bookViewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = "librarys/{libraryId}",
            arguments = listOf(navArgument("libraryId"){
                type = NavType.IntType
            })
        ) {
            val libraryId = it.arguments?.getInt("libraryId")?:0
            val bookViewModel = BookModel(appLocale = LocalConfiguration.current.locales[0])
            val libraryViewModel = LibraryModel(appLocale = LocalConfiguration.current.locales[0])

            BookList(
                bookViewModel = bookViewModel,
                libraryViewModel = libraryViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }
    }
}