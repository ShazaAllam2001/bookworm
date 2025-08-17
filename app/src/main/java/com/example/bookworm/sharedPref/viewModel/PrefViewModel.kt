package com.example.bookworm.sharedPref.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.data.User
import kotlinx.coroutines.launch

class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    private lateinit var user: User

    init {
        viewModelScope.launch {
            user = prefRepo.readPreferences()
            Log.d("user", user.toString())
        }
    }

    suspend fun savePreferences(
        uid: String,
        displayName: String,
        email: String,
        photoUrl: String,
        token: String
    ) {
       prefRepo.savePreferences(uid, displayName, email, photoUrl, token)
    }

    fun getUser(): User {
        return user
    }
}