package com.example.bookworm.feature.user.domain.model.userData

import com.example.bookworm.modules.user.data.model.UserData

sealed class UserDataResult {
    data class Success(val user: UserData): UserDataResult()
    data class Error(val message: String): UserDataResult()
}
