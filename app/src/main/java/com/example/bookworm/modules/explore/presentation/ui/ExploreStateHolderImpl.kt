package com.example.bookworm.modules.explore.presentation.ui

import com.example.bookworm.modules.explore.presentation.model.ExploreUiModel
import com.example.bookworm.modules.explore.presentation.model.ExploreUiState
import com.example.bookworm.modules.explore.presentation.state.ExploreStateHolder
import com.example.bookworm.modules.explore.presentation.state.events.ExploreStateHolderEvents
import com.example.bookworm.modules.user.presentation.model.UserDataUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ExploreStateHolderImpl @Inject constructor(): ExploreStateHolder {
    private val _events: Channel<ExploreStateHolderEvents> = Channel(Channel.UNLIMITED)
    override val events: Flow<ExploreStateHolderEvents>
        get() = _events.receiveAsFlow()

    private val _state: MutableStateFlow<ExploreUiState> = MutableStateFlow(getInitialState())
    override val state: StateFlow<ExploreUiState>
        get() = _state.asStateFlow()

    override fun updateUserState(userData: UserDataUiModel) {
        _state.update {
            it.copy(
                categories = userData.categories
            )
        }
    }

    override fun updateBooksState(books: ExploreUiModel?) {
        _state.update {
            it.copy(
                isLoading = false,
                books = books
            )
        }
    }

    private fun onRefresh() {
        _events.trySend(ExploreStateHolderEvents.RefreshScreen)
    }

    private fun onSearchClicked(searchTerms: String) {
        _state.update {
            it.copy(
                searchTerms = searchTerms
            )
        }
        _events.trySend(ExploreStateHolderEvents.SearchBooks)
    }

    private fun getInitialState(): ExploreUiState {
        return ExploreUiState(
            isLoading = true,
            categories = emptyList(),
            onRefresh = ::onRefresh,
            onSearchClicked = ::onSearchClicked
        )
    }
}