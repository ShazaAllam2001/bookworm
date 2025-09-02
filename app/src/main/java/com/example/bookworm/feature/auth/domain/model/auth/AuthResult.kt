package com.example.bookworm.feature.auth.domain.model.auth

sealed class AuthResult {
    data class Success(val user: User): AuthResult()
    data class Error(val message: String): AuthResult()
    data object Loading: AuthResult()
}