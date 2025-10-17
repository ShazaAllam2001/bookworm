package com.example.bookworm.modules.explore.presentation.state

import com.example.bookworm.modules.explore.presentation.model.ExploreUiModel
import com.example.bookworm.modules.explore.presentation.model.ExploreUiState
import com.example.bookworm.modules.explore.presentation.state.events.ExploreStateHolderEvents
import com.example.bookworm.modules.user.presentation.model.UserDataUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ExploreStateHolder {
    val events: Flow<ExploreStateHolderEvents>
    val state: StateFlow<ExploreUiState>

    fun updateUserState(userData: UserDataUiModel)
    fun updateBooksState(books: ExploreUiModel?)
}