package com.example.bookworm.feature.auth.domain.repository

import com.example.bookworm.feature.auth.domain.model.AuthResult

interface AuthRepository {
    suspend fun signIn(): AuthResult
    suspend fun signOut(): AuthResult
}