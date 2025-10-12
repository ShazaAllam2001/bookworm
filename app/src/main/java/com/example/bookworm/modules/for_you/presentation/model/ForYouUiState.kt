package com.example.bookworm.modules.for_you.presentation.model

data class ForYouUiState(
    val isLoading: Boolean = false,
    val name: String,
    val categories: List<String>,
    val books: ForYouUiModel? = null,
    val errorMessage: String? = null,
    val onRefresh: () -> Unit
)