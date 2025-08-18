package com.example.bookworm.sharedPref.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import com.example.bookworm.sharedPref.data.User
import kotlinx.coroutines.launch

class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    private lateinit var user: User

    suspend fun savePreferences(
        uid: String,
        displayName: String,
        email: String,
        photoUrl: String,
        notify: Boolean = false,
        token: String
    ) {
       prefRepo.savePreferences(uid, displayName, email, photoUrl, notify, token)
    }

    suspend fun saveNotify(
        notify: Boolean = false
    ) {
        prefRepo.savePreferences(user.uid, user.displayName, user.email, user.photoUrl, notify, user.token)
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