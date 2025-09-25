package com.example.bookworm.feature.auth.domain.repository

import android.app.Activity
import com.example.bookworm.feature.auth.domain.model.User

interface AuthRepository {
    suspend fun signIn(context: Activity): Result<User>
    suspend fun signOut(): Result<User>
}