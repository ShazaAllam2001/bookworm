package com.example.bookworm.feature.main.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.feature.books.ui.bookDetails.BookDetails
import com.example.bookworm.feature.libraries.ui.libraryBooks.BookList
import com.example.bookworm.feature.books.ui.explore.Explore
import com.example.bookworm.feature.books.ui.foryou.ForYou
import com.example.bookworm.feature.libraries.ui.myLibrary.MyLibrary
import com.example.bookworm.feature.settings.ui.settings.Settings
import com.example.bookworm.feature.libraries.ui.LibraryViewModel
import com.example.bookworm.common.constants.BottomBarTabs
import com.example.bookworm.feature.auth.ui.loggedin.LoggedInViewModel
import com.example.bookworm.feature.auth.ui.login.LoginViewModel
import com.example.bookworm.feature.books.ui.BookViewModel
import java.util.Locale


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val currentLocale = LocalConfiguration.current.locales[0] ?: Locale.getDefault()
    var updateForYou by rememberSaveable { mutableStateOf(true) }
    var updateLibrary by rememberSaveable { mutableStateOf(false) }

    val forYouBookViewModel: BookViewModel = hiltViewModel()
    val exploreBookViewModel: BookViewModel = hiltViewModel()
    val libraryViewModel: LibraryViewModel = hiltViewModel()
    val loggedInViewModel: LoggedInViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = BottomBarTabs.ForYou.route
    ) {
        composable(route = BottomBarTabs.ForYou.route) {
            ForYou(
                bookViewModel = forYouBookViewModel,
                loggedInViewModel = loggedInViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarTabs.Explore.route) {
            Explore(
                bookViewModel = forYouBookViewModel,
                loggedInViewModel = loggedInViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarTabs.MyLibrary.route) {
            if (updateLibrary) {
                //libraryViewModel.fetchLibraries()
                updateLibrary = false
            }
            MyLibrary(
                viewModel = libraryViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarTabs.Settings.route) {
            Settings(
                loggedInViewModel = loggedInViewModel,
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
            //libraryViewModel.getLibraryBooks(libraryId)

            BookList(
                libraryViewModel = libraryViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }
    }
}