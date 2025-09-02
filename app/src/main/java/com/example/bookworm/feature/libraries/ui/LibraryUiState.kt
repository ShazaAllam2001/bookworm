package com.example.bookworm.feature.libraries.ui

import com.example.bookworm.feature.books.domain.model.BookItem
import com.example.bookworm.feature.libraries.domain.model.Shelf

data class LibraryUiState(
    val isLoading: Boolean = false,
    val modified: Boolean = false,
    val libraries: List<Shelf>? = null,
    val books: List<BookItem>? = null,
    val errorMessage: String? = null
)