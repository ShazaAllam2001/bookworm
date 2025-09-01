package com.example.bookworm.feature.books.domain.model

sealed class BooksResult {
    data class Success(val message: List<BookItem>): BooksResult()
    data class Error(val message: String?): BooksResult()
    data object Loading: BooksResult()
}

sealed class BookIdResult {
    data class Success(val message: BookItem): BookIdResult()
    data class Error(val message: String?): BookIdResult()
    data object Loading: BookIdResult()
}