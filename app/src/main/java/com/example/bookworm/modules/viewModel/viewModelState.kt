package com.example.bookworm.modules.viewModel

sealed interface BooksUiState {
    data class Success(val msg: List<BookItem>) : BooksUiState
    data class Error(val msg: String?) : BooksUiState
    data object Loading : BooksUiState
}