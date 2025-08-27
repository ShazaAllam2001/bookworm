package com.example.bookworm.activities.login.modules.data.preferences


data class UserPref(
    val uid: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)