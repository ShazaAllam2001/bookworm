package com.example.bookworm.feature.user.domain.model.userData

sealed class UserDataResult {
    data class Success(val user: UserData): UserDataResult()
    data class Error(val message: String): UserDataResult()
}
