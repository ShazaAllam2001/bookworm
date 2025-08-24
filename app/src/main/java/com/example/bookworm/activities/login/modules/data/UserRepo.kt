package com.example.bookworm.activities.login.modules.data

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bookworm.activities.login.LoginActivity
import com.example.bookworm.activities.main.MainActivity
import com.example.bookworm.activities.redirect.OAuthRedirectActivity
import com.example.bookworm.sharedPref.data.PrefRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.auth


class UserRepo(private val context: Context) {
    private lateinit var prefRepo: PrefRepo

    fun launchAuthBrowser() {
        val intent = Intent(context, OAuthRedirectActivity::class.java)
        context.startActivity(intent)
    }

    suspend fun handleAuthSuccess(user: FirebaseUser?, credential: OAuthCredential?) {
        prefRepo = PrefRepo(context)

        var token = ""
        credential?.accessToken?.let { accessToken ->
            Log.d("Auth", "Access Token: $accessToken")
            token = accessToken
        }
        user?.let {
            // Access user information
            val uid = it.uid
            val displayName = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl
            Log.d("UserInfo", "uid: $uid, displayName: $displayName, email: $email, photoUrl: $photoUrl")
            // Read from Datastore
            val preferences = prefRepo.readPreferences()
            val categories = preferences.categories
            val notify = preferences.notify
            val name = if (preferences.displayName == "") displayName else preferences.displayName
            // Save to DataStore
            prefRepo.savePreferences(uid = uid, displayName = name?:"", email = email?:"",
                photoUrl = photoUrl.toString(), categories = categories, notify = notify, token = token)
            //Log.d("UserInfo Returned", prefRepo.readPreferences().toString())
            // Navigate to main screen
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    suspend fun signOut() {
        Firebase.auth.signOut()
        prefRepo = PrefRepo(context)
        val user = prefRepo.readPreferences()
        prefRepo.savePreferences(uid = user.uid, displayName = user.displayName, email = user.email,
            photoUrl = user.photoUrl, categories = user.categories, notify = user.notify, token = "")
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}