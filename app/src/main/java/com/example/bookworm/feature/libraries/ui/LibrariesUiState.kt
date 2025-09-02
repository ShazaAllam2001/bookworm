package com.example.bookworm.feature.libraries.ui

import com.example.bookworm.feature.libraries.domain.model.Shelf


sealed interface LibrariesUiState {
    data class Success(val msg: List<Shelf>) : LibrariesUiState
    data class Error(val msg: String?) : LibrariesUiState
    data object Loading : LibrariesUiState
}