package com.example.bookworm.feature.auth.domain.model

data class UserData(
    val displayName: String,
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)