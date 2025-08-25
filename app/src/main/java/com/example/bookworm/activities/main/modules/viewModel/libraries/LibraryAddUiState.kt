package com.example.bookworm.activities.main.modules.viewModel.libraries

sealed interface LibraryAddUiState {
    data object Success: LibraryAddUiState
    data class Error(val msg: String?) : LibraryAddUiState
    data object Loading : LibraryAddUiState
}