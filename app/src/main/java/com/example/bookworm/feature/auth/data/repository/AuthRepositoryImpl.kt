package com.example.bookworm.feature.auth.data.repository

import android.app.Activity
import com.example.bookworm.feature.auth.data.remote.AuthDataSource
import com.example.bookworm.feature.auth.domain.model.User
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun signIn(context: Activity): Result<User> {
        return authDataSource.signIn(context)
    }

    override suspend fun signOut(): Result<User> {
        return authDataSource.signOut()
    }
}