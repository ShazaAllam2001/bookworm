package com.example.bookworm.modules.for_you.presentation.viewModel.events

sealed interface ForYouViewModelEvents {
    data class OpenBookDetails(val id: String): ForYouViewModelEvents
}