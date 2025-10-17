package com.example.bookworm.modules.explore.presentation.state.events

sealed interface ExploreStateHolderEvents {
    data object SearchBooks : ExploreStateHolderEvents
    data object RefreshScreen : ExploreStateHolderEvents
}