package com.example.bookworm.feature.libraries.ui

sealed interface LibraryRemoveUiState {
    data object Success: LibraryRemoveUiState
    data class Error(val msg: String?) : LibraryRemoveUiState
    data object Loading : LibraryRemoveUiState
}