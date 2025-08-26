package com.example.bookworm.activities.login.modules.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthCredential
import kotlinx.coroutines.launch


class UserViewModel(private val userRepo: UserRepo): ViewModel() {
    fun launchAuthBrowser() {
        userRepo.launchAuthBrowser()
    }

    fun handleAuthSuccess(user: FirebaseUser?, credential: OAuthCredential?, expirationTime: Long) {
        viewModelScope.launch {
            userRepo.handleAuthSuccess(user, credential, expirationTime)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userRepo.signOut()
        }
    }
}