package com.example.bookworm.feature.libraries.domain.model

sealed class ModifyLibraryResult {
    data class Success(val message: Boolean): ModifyLibraryResult()
    data class Error(val message: String?): ModifyLibraryResult()
}