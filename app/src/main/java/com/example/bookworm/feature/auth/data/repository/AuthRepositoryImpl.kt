package com.example.bookworm.feature.auth.data.repository

import android.app.Activity
import com.example.bookworm.feature.auth.data.mapper.AuthMapper
import com.example.bookworm.feature.auth.domain.model.AuthResult
import com.example.bookworm.feature.auth.domain.model.User
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val context: Activity,
    private val authMapper: AuthMapper
) : AuthRepository {

    override suspend fun signIn(): AuthResult {
        return try {
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
            val user = authMapper.mapToUser(firebaseUser, credential)
            AuthResult.Success(user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign in failed")
        }
    }

    override suspend fun signOut(): AuthResult {
        return try {
            Firebase.auth.signOut()
            AuthResult.Success(User.empty())
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign out failed")
        }
    }
}