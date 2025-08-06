package com.example.bookworm.activities.login.modules.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bookworm.activities.login.modules.data.UserRepo
import kotlinx.coroutines.launch


class UserViewModel(private val userRepo: UserRepo): ViewModel() {

    fun signup(email: String, password: String, navController: NavHostController) {
        userRepo.signup(email, password, navController)
    }

    fun login(email: String, password: String) {
        userRepo.login(email, password)
    }

    suspend fun getToken(): String? {
        var token: String? = null
        val job = viewModelScope.launch{
            token = userRepo.getFirebaseToken()
        }
        job.join()
        return token
    }
}