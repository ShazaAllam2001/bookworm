package com.example.bookworm.feature.auth.domain.model.auth

data class User(
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String,
    val token: String,
    val expirationTimeStamp: Long,
    val categories: List<String> = emptyList(),
    val notify: Boolean = false,
    val isAuthenticated: Boolean
) {
    companion object {
        fun empty(): User {
            return User(uid = "", displayName = "", email = "", photoUrl = "",
                token = "", expirationTimeStamp = 0, isAuthenticated = false)
        }
    }
}