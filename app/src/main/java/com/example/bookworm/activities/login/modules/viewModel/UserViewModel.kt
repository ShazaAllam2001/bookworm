package com.example.bookworm.activities.login.modules.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bookworm.activities.login.modules.data.OAuthRepo
import com.example.bookworm.activities.login.modules.data.UserRepo
import kotlinx.coroutines.launch

enum class LoginType {
    Google,
    Email
}

class UserViewModel(
    private val userRepo: UserRepo,
    private val oAuthRepo: OAuthRepo
): ViewModel() {
    var loginType: LoginType? = null

    fun signup(email: String, password: String, navController: NavHostController) {
        userRepo.signup(email, password, navController)
    }

    fun login(email: String, password: String) {
        loginType = LoginType.Email
        userRepo.login(email, password)
    }

    fun signOut() {
        userRepo.signOut()
    }

    suspend fun loginWithGoogle(): Boolean {
        loginType = LoginType.Google
       return userRepo.loginWithGoogle()
    }

    suspend fun signOutWithGoogle() {
        userRepo.signOutWithGoogle()
    }

    fun launchBrowser() {
        oAuthRepo.launchAuthBrowser()
    }
}