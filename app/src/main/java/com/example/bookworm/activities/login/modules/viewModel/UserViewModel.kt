package com.example.bookworm.activities.login.modules.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.google.firebase.auth.FirebaseUser


class UserViewModel(private val userRepo: UserRepo): ViewModel() {
    fun launchAuthBrowser() {
        userRepo.launchAuthBrowser()
    }

    fun handleAuthSuccess(user: FirebaseUser?) {
        userRepo.handleAuthSuccess(user)
    }

    fun signOut() {
        userRepo.signOut()
    }
}