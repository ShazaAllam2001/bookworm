package com.example.bookworm.activities.login.modules.data.fireauth

import android.content.Context
import android.content.Intent
import com.example.bookworm.feature.auth.ui.LoginActivity
import com.example.bookworm.activities.main.MainActivity
import com.example.bookworm.feature.auth.ui.OAuthRedirectActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class UserRepo(private val context: Context) {

    fun launchAuthBrowser() {
        val intent = Intent(context, OAuthRedirectActivity::class.java)
        context.startActivity(intent)
    }

    fun handleAuthSuccess() {
        // Navigate to main screen
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun signOut() {
        Firebase.auth.signOut()
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}