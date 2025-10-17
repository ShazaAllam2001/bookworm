package com.example.bookworm.modules.for_you.presentation.ui

import com.example.bookworm.modules.for_you.presentation.model.ForYouUiModel
import com.example.bookworm.modules.for_you.presentation.model.ForYouUiState
import com.example.bookworm.modules.for_you.presentation.state.ForYouStateHolder
import com.example.bookworm.modules.for_you.presentation.state.events.ForYouStateHolderEvents
import com.example.bookworm.modules.user.presentation.model.UserDataUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ForYouStateHolderImpl @Inject constructor(): ForYouStateHolder {
    private val _events: Channel<ForYouStateHolderEvents> = Channel(Channel.UNLIMITED)
    override val events: Flow<ForYouStateHolderEvents>
        get() = _events.receiveAsFlow()

    private val _state: MutableStateFlow<ForYouUiState> = MutableStateFlow(getInitialState())
    override val state: StateFlow<ForYouUiState>
        get() = _state.asStateFlow()

    override fun updateUserState(userData: UserDataUiModel) {
        _state.update {
            it.copy(
                name = userData.displayName,
                categories = userData.categories
            )
        }
    }

    override fun updateBooksState(books: ForYouUiModel?) {
        _state.update {
            it.copy(
                isLoading = false,
                books = books
            )
        }
    }

    private fun onRefresh() {
        _events.trySend(ForYouStateHolderEvents.RefreshScreen)
    }

    private fun getInitialState(): ForYouUiState {
        return ForYouUiState(
            isLoading = true,
            name = "",
            categories = emptyList(),
            onRefresh = ::onRefresh
        )
    }
}