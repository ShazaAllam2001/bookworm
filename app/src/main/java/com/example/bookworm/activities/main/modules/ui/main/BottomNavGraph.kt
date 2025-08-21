package com.example.bookworm.activities.main.modules.ui.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.example.bookworm.activities.main.modules.ui.bookGrid.components.BookDetails
import com.example.bookworm.activities.main.modules.ui.myLibrary.components.libraryBooks.BookList
import com.example.bookworm.activities.main.modules.ui.explore.Explore
import com.example.bookworm.activities.main.modules.ui.foryou.ForYou
import com.example.bookworm.activities.main.modules.ui.myLibrary.MyLibrary
import com.example.bookworm.activities.main.modules.ui.settings.Settings
import com.example.bookworm.activities.main.modules.viewModel.books.BookModel
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.viewModel.PrefViewModel
import java.util.Locale


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val currentLocale = LocalConfiguration.current.locales[0] ?: Locale.getDefault()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ForYou.route
    ) {
        composable(route = BottomBarScreen.ForYou.route) {
            val bookViewModel = BookModel(appLocale = currentLocale)
            ForYou(
                viewModel = bookViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Explore.route) {
            val  bookViewModel = BookModel(appLocale = currentLocale)
            Explore(
                viewModel = bookViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            val libraryViewModel = LibraryModel(
                appLocale = currentLocale,
                prefRepo = PrefRepo(context)
            )
            MyLibrary(
                viewModel = libraryViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            val userViewModel = UserViewModel(
                userRepo = UserRepo(context)
            )
            val prefViewModel = PrefViewModel(
                prefRepo = PrefRepo(context)
            )
            Settings(
                userViewModel = userViewModel,
                prefViewModel = prefViewModel
            )
        }
        composable(
            route = "books/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.StringType
            })
        ) {
            val bookId = it.arguments?.getString("bookId")?:""
            val viewModel = BookModel(appLocale = currentLocale)
            viewModel.searchBookById(bookId)
            val libraryViewModel = LibraryModel(
                appLocale = currentLocale,
                prefRepo = PrefRepo(context)
            )

            BookDetails(
                bookViewModel = viewModel,
                libraryViewModel = libraryViewModel,
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
            val libraryViewModel = LibraryModel(
                appLocale = currentLocale,
                prefRepo = PrefRepo(context)
            )
            libraryViewModel.getLibraryBooks(libraryId)

            BookList(
                libraryViewModel = libraryViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }
    }
}