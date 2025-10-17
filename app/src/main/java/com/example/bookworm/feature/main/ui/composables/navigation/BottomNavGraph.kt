package com.example.bookworm.feature.main.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookworm.feature.books.ui.composables.bookDetails.BookDetails
import com.example.bookworm.feature.libraries.ui.composables.libraryBooks.BookList
import com.example.bookworm.modules.explore.presentation.ui.Explore
import com.example.bookworm.modules.for_you.presentation.ui.ForYou
import com.example.bookworm.feature.libraries.ui.composables.myLibrary.MyLibrary
import com.example.bookworm.feature.settings.ui.composables.Settings
import com.example.bookworm.common.constants.BottomBarTabs
import com.example.bookworm.common.constants.Screens


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    onNavigateToLogin: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = BottomBarTabs.ForYou.route
    ) {
        composable(route = BottomBarTabs.ForYou.route) {
            ForYou(
                navController = navController
            )
        }
        composable(route = BottomBarTabs.Explore.route) {
            Explore(
                navController = navController
            )
        }
        composable(route = BottomBarTabs.MyLibrary.route) {
            MyLibrary(
                navController = navController
            )
        }
        composable(route = BottomBarTabs.Settings.route) {
            Settings(
                onNavigateToLogin = onNavigateToLogin
            )
        }
        composable(
            route = Screens.Books.route,
            arguments = listOf(navArgument(Screens.Books.parameter){
                type = NavType.StringType
            })
        ) {
            val bookId = it.arguments?.getString(Screens.Books.parameter)?:""

            BookDetails(
                bookId = bookId,
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

            BookList(
                libraryId = libraryId,
                navController = navController
            )
        }
    }
}