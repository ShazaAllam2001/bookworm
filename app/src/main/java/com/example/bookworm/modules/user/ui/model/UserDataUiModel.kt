package com.example.bookworm.modules.user.ui.model

data class UserDataUiModel(
    val displayName: String = "",
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)