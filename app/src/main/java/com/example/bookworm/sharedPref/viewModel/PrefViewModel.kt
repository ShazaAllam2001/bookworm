package com.example.bookworm.sharedPref.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.data.User
import kotlinx.coroutines.launch


class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    var user: User? = null

    fun savePreferences(
        uid: String,
        displayName: String,
        email: String,
        photoUrl: String,
        token: String
    ) {
        viewModelScope.launch {
            prefRepo.savePreferences(uid, displayName, email, photoUrl, token)
        }
    }

    suspend fun readPreferences() {
        val job = viewModelScope.launch {
            user = prefRepo.readPreferences()
        }
        job.join()
    }
}