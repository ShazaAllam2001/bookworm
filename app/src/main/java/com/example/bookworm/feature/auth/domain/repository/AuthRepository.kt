package com.example.bookworm.feature.auth.domain.repository

import android.app.Activity
import com.example.bookworm.feature.auth.domain.model.auth.AuthResult
import com.example.bookworm.feature.auth.domain.model.auth.User

interface AuthRepository {
    suspend fun signIn(context: Activity): Result<User>
    suspend fun signOut(): Result<User>
}