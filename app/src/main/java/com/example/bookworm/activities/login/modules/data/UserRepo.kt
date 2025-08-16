package com.example.bookworm.activities.login.modules.data

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bookworm.activities.main.MainActivity
import com.example.bookworm.activities.redirect.OAuthRedirectActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth


class UserRepo(private val context: Context) {
    fun launchAuthBrowser() {
        val intent = Intent(context, OAuthRedirectActivity::class.java)
        context.startActivity(intent)
    }

    fun handleAuthSuccess(user: FirebaseUser?) {
        user?.let {
            // Access user information
            val uid = it.uid
            val displayName = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl
            // Update UI or navigate to main screen
            Log.d("UserInfo", "displayName: $displayName, email: $email, photoUrl: $photoUrl")
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }
}