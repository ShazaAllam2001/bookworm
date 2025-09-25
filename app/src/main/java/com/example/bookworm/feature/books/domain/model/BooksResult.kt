package com.example.bookworm.feature.books.domain.model

import com.example.bookworm.feature.books.data.model.BookItem

sealed class BooksResult {
    data class Success(val message: List<BookItem>): BooksResult()
    data class Error(val message: String?): BooksResult()
}