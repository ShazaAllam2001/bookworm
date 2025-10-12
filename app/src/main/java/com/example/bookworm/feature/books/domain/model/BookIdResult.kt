package com.example.bookworm.feature.books.domain.model

import com.example.bookworm.feature.books.data.model.BookItem

sealed class BookIdResult {
    data class Success(val message: BookItem?): BookIdResult()
    data class Error(val message: String?): BookIdResult()
}