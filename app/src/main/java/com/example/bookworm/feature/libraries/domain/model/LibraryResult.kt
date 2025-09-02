package com.example.bookworm.feature.libraries.domain.model

sealed class LibraryResult {
    data class Success(val message: List<Shelf>): LibraryResult()
    data class Error(val message: String?): LibraryResult()
    data object Loading: LibraryResult()
}