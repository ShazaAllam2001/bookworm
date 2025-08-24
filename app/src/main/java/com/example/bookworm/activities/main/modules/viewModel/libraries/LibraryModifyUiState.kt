package com.example.bookworm.activities.main.modules.viewModel.libraries

sealed interface LibraryModifyUiState {
    data object Success: LibraryModifyUiState
    data class Error(val msg: String?) : LibraryModifyUiState
    data object Loading : LibraryModifyUiState
}