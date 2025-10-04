package com.example.bookworm.modules.for_you.presentation.state

import com.example.bookworm.modules.for_you.presentation.model.ForYouUiModel
import com.example.bookworm.modules.for_you.presentation.model.ForYouUiState
import com.example.bookworm.modules.for_you.presentation.state.events.ForYouStateHolderEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ForYouStateHolder {
    val events: Flow<ForYouStateHolderEvents>
    val state: StateFlow<ForYouUiState>

    fun updateBooksState(books: ForYouUiModel?)
}