package com.example.bookworm.feature.libraries.domain.model

import com.example.bookworm.feature.libraries.data.model.Shelf

sealed class LibraryResult {
    data class Success(val message: List<Shelf>): LibraryResult()
    data class Error(val message: String?): LibraryResult()
}