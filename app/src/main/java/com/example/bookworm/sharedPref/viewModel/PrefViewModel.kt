package com.example.bookworm.sharedPref.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.data.User
import kotlinx.coroutines.launch

class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    private lateinit var user: User

    fun savePreferences(
        uid: String,
        displayName: String,
        email: String,
        photoUrl: String,
        categories: List<String> = emptyList(),
        notify: Boolean = false,
        token: String
    ) {
        viewModelScope.launch {
            prefRepo.savePreferences(uid, displayName, email, photoUrl, categories, notify, token)
        }
    }

    fun saveName(displayName: String) {
        viewModelScope.launch {
            prefRepo.savePreferences(user.uid, displayName, user.email, user.photoUrl, user.categories, user.notify, user.token)
        }
    }

    fun saveCategories(categories: List<String>) {
        viewModelScope.launch {
            prefRepo.savePreferences(user.uid, user.displayName, user.email, user.photoUrl, categories, user.notify, user.token)
        }
    }

    fun saveNotify(notify: Boolean = false) {
        viewModelScope.launch {
            prefRepo.savePreferences(user.uid, user.displayName, user.email, user.photoUrl, user.categories, notify, user.token)
        }
    }

    suspend fun readPreferences(): User {
        val job = viewModelScope.launch {
            user = prefRepo.readPreferences()
            Log.d("user", user.toString())
        }
        job.join()
        return user
    }
}