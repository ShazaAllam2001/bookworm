package com.example.bookworm.modules.for_you.presentation.state.events

sealed interface ForYouStateHolderEvents {
    data object RefreshScreen : ForYouStateHolderEvents
}