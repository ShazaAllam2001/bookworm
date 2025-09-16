package com.example.bookworm.feature.user.domain.model.userData

data class UserData(
    val displayName: String = "",
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)