package com.example.bookworm.activities.login.modules.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import kotlinx.coroutines.launch

enum class LoginType {
    Google,
    Email
}

class UserViewModel(private val userRepo: UserRepo): ViewModel() {
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

    suspend fun getToken(): String? {
        var token: String? = null
        val job = viewModelScope.launch{
            token = userRepo.getIdToken()
        }
        job.join()
        return token
    }

    suspend fun loginWithGoogle(): Boolean {
        loginType = LoginType.Google
       return userRepo.loginWithGoogle()
    }

    suspend fun signOutWithGoogle() {
        userRepo.signOutWithGoogle()
    }
}