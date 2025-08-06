package com.example.bookworm.activities.main.modules.ui.main

import android.util.Log
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.activities.main.modules.ui.bookGrid.components.BookDetails
import com.example.bookworm.activities.main.modules.ui.myLibrary.components.BookList
import com.example.bookworm.activities.main.modules.ui.explore.Explore
import com.example.bookworm.activities.main.modules.ui.foryou.ForYou
import com.example.bookworm.activities.main.modules.ui.myLibrary.MyLibrary
import com.example.bookworm.activities.main.modules.ui.settings.Settings
import com.example.bookworm.activities.main.modules.viewModel.books.BookModel
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.viewModel.PrefViewModel


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ForYou.route
    ) {
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
            val prefViewModel = PrefViewModel(PrefRepo(LocalContext.current))
            val token by prefViewModel.token.collectAsState()
            val libraryViewModel = LibraryModel(
                appLocale = LocalConfiguration.current.locales[0],
                token = token
            )
            MyLibrary(
                viewModel = libraryViewModel,
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
            val sharedPref = LocalContext.current.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val libraryId = it.arguments?.getInt("libraryId")?:0
            val libraryViewModel = sharedPref.getString("user_token", "")?.let { item ->
                LibraryModel(
                    appLocale = LocalConfiguration.current.locales[0],
                    token = item
                )
            }
            libraryViewModel?.getLibraryBooks(libraryId)

            if (libraryViewModel != null) {
                BookList(
                    libraryViewModel = libraryViewModel,
                    libraryId = libraryId,
                    navController = navController
                )
            }
        }
    }
}