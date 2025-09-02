package com.example.bookworm.feature.libraries.ui

sealed interface LibraryAddUiState {
    data object Success: LibraryAddUiState
    data class Error(val msg: String?) : LibraryAddUiState
    data object Loading : LibraryAddUiState
}