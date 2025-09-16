package com.example.bookworm.feature.user.data.mapper

import android.util.Log
import com.example.bookworm.feature.auth.domain.model.User
import com.example.bookworm.feature.user.domain.model.userData.UserData
import com.example.bookworm.feature.user.domain.repository.UserDataRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.OAuthCredential
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun mapToUser(firebaseUser: FirebaseUser?, tokenResult: GetTokenResult?, credential: OAuthCredential?): User {
        val userData = firebaseUser?.run {
            val uid = uid
            val displayName = displayName ?: ""
            val email = email ?: ""
            val photoUrl = photoUrl?.toString() ?: ""
            val expirationTimestamp = tokenResult?.expirationTimestamp ?: 0L
            val token = credential?.accessToken ?: ""

            Log.d("Auth", "Access Token: $token")
            Log.d("Auth", "Expiration Time: $expirationTimestamp")

            User(
                uid = uid,
                displayName = displayName,
                email = email,
                photoUrl = photoUrl,
                token = token,
                expirationTimeStamp = expirationTimestamp,
                isAuthenticated = true
            )
        } ?: User(
            uid = "",
            displayName = "",
            email = "",
            photoUrl = "",
            token = "",
            expirationTimeStamp = 0L,
            isAuthenticated = true
        )

        return userData
    }

    fun mapToUserData(map: Map<String, Any>?): UserData {
        val userData =  UserData(
            displayName = map?.get(UserDataRepository.NAME) as? String ?: "",
            categories = (map?.get(UserDataRepository.CATEGORIES) as? List<*>)?.filterIsInstance<String>() ?: emptyList(),
            notify = map?.get(UserDataRepository.NOTIFY) as? Boolean ?: false
        )
        return userData
    }
}