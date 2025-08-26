package com.example.bookworm.activities.main.modules.viewModel.libraries

sealed interface LibraryCheckUiState {
    data class Success(val msg: Boolean): LibraryCheckUiState
    data class Error(val msg: String?) : LibraryCheckUiState
    data object Loading : LibraryCheckUiState
}