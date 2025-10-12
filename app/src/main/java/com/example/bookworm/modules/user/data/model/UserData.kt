package com.example.bookworm.modules.user.data.model

import com.google.firebase.firestore.DocumentSnapshot
import com.example.bookworm.modules.user.domain.model.UserDataDomainModel
import com.example.bookworm.modules.user.domain.repository.UserDataRepository

data class UserData(
    val displayName: String = "",
    val categories: List<String> = emptyList(),
    val notify: Boolean = false
)

fun DocumentSnapshot.toUserData() : UserData {
    return UserData(
        displayName = get(UserDataRepository.NAME) as? String ?: "",
        categories = (get(UserDataRepository.CATEGORIES) as? List<*>)?.filterIsInstance<String>() ?: emptyList(),
        notify = get(UserDataRepository.NOTIFY) as? Boolean ?: false
    )
}

fun UserData.toDomain(): UserDataDomainModel {
    return UserDataDomainModel(
        displayName = displayName,
        categories = categories,
        notify = notify
    )
}