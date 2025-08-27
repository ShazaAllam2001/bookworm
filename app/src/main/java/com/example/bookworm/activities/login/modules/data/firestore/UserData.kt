package com.example.bookworm.activities.login.modules.data.firestore

data class UserData(
    val displayName: String = "",
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)