package com.example.bookworm.feature.main.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.feature.books.ui.composables.bookDetails.BookDetails
import com.example.bookworm.feature.libraries.ui.composables.libraryBooks.BookList
import com.example.bookworm.feature.books.ui.composables.explore.Explore
import com.example.bookworm.feature.books.ui.composables.foryou.ForYou
import com.example.bookworm.feature.libraries.ui.composables.myLibrary.MyLibrary
import com.example.bookworm.feature.settings.ui.composables.Settings
import com.example.bookworm.feature.libraries.ui.viewModel.LibraryViewModel
import com.example.bookworm.common.constants.BottomBarTabs
import com.example.bookworm.common.constants.Screens
import com.example.bookworm.feature.user.ui.viewModel.LoggedInViewModel
import com.example.bookworm.feature.books.ui.viewModel.BookViewModel
import com.example.bookworm.feature.notifications.ui.viewModel.NotifyViewModel


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    onNavigateToLogin: () -> Unit
) {
    var updateForYou by rememberSaveable { mutableStateOf(true) }
    var updateLibrary by rememberSaveable { mutableStateOf(false) }

    val forYouBookViewModel: BookViewModel = hiltViewModel()
    val exploreBookViewModel: BookViewModel = hiltViewModel()
    val libraryViewModel: LibraryViewModel = hiltViewModel()
    val loggedInViewModel: LoggedInViewModel = hiltViewModel()
    val notifyViewModel: NotifyViewModel = hiltViewModel()

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
                libraryViewModel.fetchLibraries()
                updateLibrary = false
            }
            MyLibrary(
                viewModel = libraryViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarTabs.Settings.route) {
            Settings(
                notifyViewModel = notifyViewModel,
                loggedInViewModel = loggedInViewModel,
                onNavigateToLogin = onNavigateToLogin,
                updateForYou = { updateForYou = true }
            )
        }
        composable(
            route = Screens.Books.route,
            arguments = listOf(navArgument(Screens.Books.parameter){
                type = NavType.StringType
            })
        ) {
            val bookId = it.arguments?.getString(Screens.Books.parameter)?:""
            exploreBookViewModel.searchBookById(bookId)

            BookDetails(
                bookViewModel = exploreBookViewModel,
                libraryViewModel = libraryViewModel,
                updateLibrary = { updateLibrary = true },
                navController = navController
            )
        }
        composable(
            route = Screens.Librarys.route,
            arguments = listOf(navArgument(Screens.Librarys.parameter){
                type = NavType.IntType
            })
        ) {
            val libraryId = it.arguments?.getInt(Screens.Librarys.parameter)?:0
            libraryViewModel.fetchLibraries(libraryId)
            libraryViewModel.getLibraryBooks(libraryId)

            BookList(
                libraryViewModel = libraryViewModel,
                navController = navController
            )
        }
    }
}