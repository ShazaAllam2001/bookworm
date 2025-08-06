package com.example.bookworm.activities.login.modules.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.example.bookworm.sharedPref.viewModel.PrefViewModel
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun LoginNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Login.route) {
            LogIn(
                navController = navController,
                userViewModel = UserViewModel(UserRepo(LocalContext.current)),
                prefViewModel = PrefViewModel(PrefRepo(LocalContext.current))
            )
        }
        composable(route = Screens.Signup.route) {
            SignUp(
                navController = navController,
                userViewModel = UserViewModel(UserRepo(LocalContext.current))
            )
        }
    }
}