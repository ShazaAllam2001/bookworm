package com.example.bookworm.feature.books.ui

import com.example.bookworm.feature.books.domain.model.BookItem

data class BookUiState(
    val isLoading: Boolean = false,
    val book: BookItem? = null,
    val books: List<BookItem>? = null,
    val errorMessage: String? = null
)