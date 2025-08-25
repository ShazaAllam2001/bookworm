package com.example.bookworm.activities.main.modules.viewModel.libraries

sealed interface LibraryRemoveUiState {
    data object Success: LibraryRemoveUiState
    data class Error(val msg: String?) : LibraryRemoveUiState
    data object Loading : LibraryRemoveUiState
}