package com.example.bookworm.activities.main.modules.viewModel.libraries


sealed interface LibrariesUiState {
    data class Success(val msg: List<Shelf>) : LibrariesUiState
    data class Error(val msg: String?) : LibrariesUiState
    data object Loading : LibrariesUiState
}