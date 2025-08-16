package com.example.bookworm.activities.redirect

import android.content.Intent
import android.util.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.bookworm.activities.login.modules.data.UserRepo
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.example.bookworm.activities.main.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class OAuthRedirectActivity : ComponentActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel(UserRepo(this))

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

                // Get the access token
                credential?.accessToken?.let { accessToken ->
                    Log.d("Auth", "Access Token: $accessToken")
                    Log.d("Auth", "Access Length: ${accessToken.length}")
                    // Now you can use this accessToken with Google APIs
                    searchBooks(accessToken, "flower")
                }
                userViewModel.handleAuthSuccess(user)
            }
            .addOnFailureListener { e ->
                when (e) {
                    is FirebaseAuthUserCollisionException ->
                        Log.e("Error","Account already exists with different credentials")
                    else -> Log.e("Error","Authentication failed: ${e.message}")
                }
            }
    }

    private fun searchBooks(accessToken: String, query: String) {
        val url = "https://www.googleapis.com/books/v1/volumes?q=$query"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API", "Request failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                Log.d("API", "Response: $responseData")
                // Parse and handle the response
            }
        })
    }
}
