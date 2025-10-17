package com.example.bookworm.modules.explore.presentation.model

data class ExploreUiState(
    val isLoading: Boolean = false,
    val categories: List<String>,
    val searchTerms: String = "",
    val books: ExploreUiModel? = null,
    val errorMessage: String? = null,
    val onRefresh: () -> Unit,
    val onSearchClicked: (String) -> Unit
)