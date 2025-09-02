package com.example.bookworm.feature.auth.data.mapper

import android.util.Log
import com.example.bookworm.feature.auth.domain.model.auth.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthCredential
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthMapper @Inject constructor() {
    suspend fun mapToUser(firebaseUser: FirebaseUser?, credential: OAuthCredential?): User {
        var uid = ""
        var displayName = ""
        var email = ""
        var photoUrl = ""
        var token = ""
        var expirationTimestamp: Long = 0
        credential?.accessToken?.let { accessToken ->
            Log.d("Auth", "Access Token: $accessToken")
            token = accessToken
        }
        firebaseUser?.let {
            uid = it.uid
            displayName = it.displayName ?: ""
            email = it.email ?: ""
            photoUrl = it.photoUrl.toString()
            val result = firebaseUser.getIdToken(true).await()
            expirationTimestamp = result.expirationTimestamp
            Log.d("Auth", "Expiration Time: $expirationTimestamp")
        }

        return User(
            uid = uid,
            displayName = displayName,
            email = email,
            photoUrl = photoUrl,
            token = token,
            expirationTimeStamp = expirationTimestamp,
            isAuthenticated = true
        )
    }
}