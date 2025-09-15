package com.example.bookworm.feature.auth.data.remote

import android.app.Activity
import com.example.bookworm.common.data.mapper.UserMapper
import com.example.bookworm.feature.auth.domain.model.auth.User
import com.google.firebase.Firebase
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val userMapper: UserMapper
)  {

    suspend fun signIn(context: Activity): Result<User> {
        return runCatching {
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
            val authResult = auth.startActivityForSignInWithProvider(context, provider).await()
            val firebaseUser = authResult.user
            val credential = authResult.credential as? OAuthCredential
            val tokenResult = firebaseUser?.getIdToken(true)?.await()
            userMapper.mapToUser(firebaseUser, tokenResult, credential)
        }
    }

    fun signOut(): Result<User> {
        return runCatching {
            Firebase.auth.signOut()
            User.empty()
        }
    }
}