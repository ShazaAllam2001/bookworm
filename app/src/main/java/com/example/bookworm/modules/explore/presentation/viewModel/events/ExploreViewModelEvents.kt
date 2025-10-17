package com.example.bookworm.modules.explore.presentation.viewModel.events

sealed interface ExploreViewModelEvents {
    data class OpenBookDetails(val id: String): ExploreViewModelEvents
}