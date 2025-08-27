package com.example.bookworm.activities.login.modules.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.activities.login.modules.data.fireauth.UserRepo
import com.example.bookworm.activities.login.modules.data.preferences.PrefRepo
import com.example.bookworm.activities.login.modules.data.firestore.DataRepo
import com.example.bookworm.activities.login.modules.data.firestore.UserData
import com.example.bookworm.activities.login.modules.data.preferences.UserPref
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthCredential
import kotlinx.coroutines.launch


class UserViewModel(
    private val userRepo: UserRepo,
    private val prefRepo: PrefRepo,
    private val dataRepo: DataRepo
): ViewModel() {
    private lateinit var userPref: UserPref
    var userData: UserData? by mutableStateOf(null)
        private set

    // Preferences Methods
    fun savePreferences(
        uid: String,
        email: String,
        photoUrl: String,
        token: String,
        expirationTimeStamp: Long
    ) {
        viewModelScope.launch {
            prefRepo.savePreferences(uid, email, photoUrl, token, expirationTimeStamp)
        }
    }

    suspend fun readPreferences(): UserPref {
        val job = viewModelScope.launch {
            userPref = prefRepo.readPreferences()
            Log.d("User Prefs", userPref.toString())
        }
        job.join()
        return userPref
    }

    // Firestore Methods
    fun saveUser(userId: String, userData: UserData) {
        viewModelScope.launch {
            dataRepo.saveUser(userId, userData)
        }
    }

    fun readUser(userId: String) {
        viewModelScope.launch {
            dataRepo.readUser(userId)
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val map = document.data
                        userData = UserData(
                            displayName = map?.get(DataRepo.NAME) as String,
                            categories = map[DataRepo.CATEGORIES] as List<String>,
                            notify = map[DataRepo.NOTIFY] as Boolean
                        )
                        Log.d("Firestore", "Document data: ${userData}\"")
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error reading document!", e)
                }
        }
    }

    fun saveName(userId: String, name: String) {
        viewModelScope.launch {
            dataRepo.saveName(userId, name)
            readUser(userId)
        }
    }

    fun saveCategories(userId: String, categories: List<String>) {
        viewModelScope.launch {
            dataRepo.saveCategories(userId, categories)
            readUser(userId)
        }
    }

    fun saveNotify(userId: String, notify: Boolean) {
        viewModelScope.launch {
            dataRepo.saveNotify(userId, notify)
            readUser(userId)
        }
    }

    // Login & SignOut Methods
    fun launchAuthBrowser() {
        userRepo.launchAuthBrowser()
    }

    fun handleAuthSuccess(user: FirebaseUser?, credential: OAuthCredential?, expirationTime: Long) {
        viewModelScope.launch {
            var token = ""
            credential?.accessToken?.let { accessToken ->
                Log.d("Auth", "Access Token: $accessToken")
                Log.d("Auth", "Expires At: $expirationTime")
                token = accessToken
            }
            user?.let {
                // Access user information
                val uid = it.uid
                val displayName = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl
                Log.d("UserInfo", "uid: $uid, displayName: $displayName, email: $email, photoUrl: $photoUrl")
                // If user is not stored in firestore, write a user document
                dataRepo.readUser(uid)
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            val map = document.data
                            userData = UserData(
                                displayName = map?.get(DataRepo.NAME) as String,
                                categories = map[DataRepo.CATEGORIES] as List<String>,
                                notify = map[DataRepo.NOTIFY] as Boolean
                            )
                            Log.d("Firestore", "Document data: ${userData}\"")
                        } else {
                            Log.d("Firestore", "No such document")
                            dataRepo.saveUser(uid, UserData("", emptyList(), false))
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firestore", "Error reading document!", e)
                    }
                // Save to DataStore
                prefRepo.savePreferences(
                    uid = uid, email = email ?: "", photoUrl = photoUrl.toString(),
                    token = token, expirationTimeStamp = expirationTime
                )
            }
            userRepo.handleAuthSuccess()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userRepo.signOut()
            prefRepo.savePreferences(uid = "", email = "", photoUrl = "", token = "", expirationTimeStamp = 0)
        }
    }
}