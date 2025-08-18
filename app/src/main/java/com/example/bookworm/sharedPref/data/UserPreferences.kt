package com.example.bookworm.sharedPref.data


data class User(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val notify: Boolean = false,
    val preferences: List<String> = emptyList(),
    val token: String = ""
)