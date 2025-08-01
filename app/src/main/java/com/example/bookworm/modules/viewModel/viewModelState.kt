package com.example.bookworm.modules.viewModel

sealed interface BooksUiState {
    data class Success(val msg: List<BookItem>) : BooksUiState
    data class Error(val msg: String?) : BooksUiState
    data object Loading : BooksUiState
}

sealed interface BookIdUiState {
    data class Success(val msg: BookItem) : BookIdUiState
    data class Error(val msg: String?) : BookIdUiState
    data object Loading: BookIdUiState
}