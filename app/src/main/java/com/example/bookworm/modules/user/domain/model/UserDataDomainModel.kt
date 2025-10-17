package com.example.bookworm.modules.user.domain.model

import com.example.bookworm.modules.user.presentation.model.UserDataUiModel

data class UserDataDomainModel(
    val displayName: String = "",
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)

fun UserDataDomainModel.toUI(): UserDataUiModel {
    return UserDataUiModel(
        displayName = displayName,
        categories = categories,
        notify = notify
    )
}