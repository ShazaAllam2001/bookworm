package com.example.bookworm.activities.redirect

import android.util.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth


class OAuthRedirectActivity : ComponentActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel(
            userRepo = UserRepo(this)
        )

        val auth = Firebase.auth
        val provider = OAuthProvider.newBuilder("google.com").apply {
            scopes = listOf(
                "email",
                "profile",
                "openid",
                "https://www.googleapis.com/auth/books"
            )
            addCustomParameter("prompt", "select_account")
        }.build()

        auth.startActivityForSignInWithProvider(this, provider)
            .addOnSuccessListener { authResult ->
                val user = authResult.user
                val credential = authResult.credential as? OAuthCredential
                userViewModel.handleAuthSuccess(user, credential)
            }
            .addOnFailureListener { e ->
                when (e) {
                    is FirebaseAuthUserCollisionException ->
                        Log.e("Error","Account already exists with different credentials")
                    else -> Log.e("Error","Authentication failed: ${e.message}")
                }
            }
    }
}
