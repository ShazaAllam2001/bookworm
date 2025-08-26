package com.example.bookworm.activities.main.modules.ui.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var updateForYou by rememberSaveable { mutableStateOf(false) }
    var updateLibrary by rememberSaveable { mutableStateOf(false) }

    val forYouBookViewModel = BookModel(
        appLocale = currentLocale,
        prefRepo = PrefRepo(context)
    )
    val exploreBookViewModel = BookModel(
        appLocale = currentLocale,
        prefRepo = PrefRepo(context)
    )
    val libraryViewModel = LibraryModel(
        appLocale = currentLocale,
        prefRepo = PrefRepo(context)
    )
    val userViewModel = UserViewModel(
        userRepo = UserRepo(context)
    )
    val prefViewModel = PrefViewModel(
        prefRepo = PrefRepo(context)
    )

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ForYou.route
    ) {
        composable(route = BottomBarScreen.ForYou.route) {
            if (updateForYou) {
                forYouBookViewModel.fetchBooksForYou()
                updateForYou = false
            }
            ForYou(
                viewModel = forYouBookViewModel,
                prefViewModel = prefViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Explore.route) {
            Explore(
                viewModel = exploreBookViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.MyLibrary.route) {
            if (updateLibrary) {
                libraryViewModel.fetchLibraries()
                updateLibrary = false
            }
            MyLibrary(
                viewModel = libraryViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings(
                userViewModel = userViewModel,
                prefViewModel = prefViewModel,
                updateForYou = { updateForYou = true }
            )
        }
        composable(
            route = "books/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.StringType
            })
        ) {
            val bookId = it.arguments?.getString("bookId")?:""
            exploreBookViewModel.searchBookById(bookId)

            BookDetails(
                bookViewModel = exploreBookViewModel,
                libraryViewModel = libraryViewModel,
                updateLibrary = { updateLibrary = true },
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
            libraryViewModel.getLibraryBooks(libraryId)

            BookList(
                libraryViewModel = libraryViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }
    }
}