package com.example.bookworm.feature.auth.domain.repository

import android.app.Activity
import com.example.bookworm.feature.auth.domain.model.auth.AuthResult

interface AuthRepository {
    suspend fun signIn(context: Activity): AuthResult
    suspend fun signOut(): AuthResult
}