package com.example.bookworm.modules.explore.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.modules.explore.domain.model.toUI
import com.example.bookworm.modules.explore.domain.request.ExploreRequest
import com.example.bookworm.modules.explore.domain.usecase.SearchBooksUseCase
import com.example.bookworm.modules.explore.domain.usecase.FetchBooksExploreUseCase
import com.example.bookworm.modules.explore.presentation.model.ExploreUiModel
import com.example.bookworm.modules.explore.presentation.state.ExploreStateHolder
import com.example.bookworm.modules.explore.presentation.state.events.ExploreStateHolderEvents
import com.example.bookworm.modules.explore.presentation.viewModel.events.ExploreViewModelEvents
import com.example.bookworm.modules.user.domain.model.toUI
import com.example.bookworm.modules.user.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val fetchBooksExploreUseCase: FetchBooksExploreUseCase,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val stateHolder: ExploreStateHolder
): ViewModel() {

    private val _events: Channel<ExploreViewModelEvents> = Channel(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()

    val state = stateHolder.state

    init {
        viewModelScope.launch {
            fetchUserData()
            fetchBooksExplore()
            observeStateHolderEvents()
        }
    }

    private suspend fun fetchUserData() {
        val result = getUserDataUseCase()
        val resultContent = result.getOrNull()
        if (result.isSuccess && resultContent != null) {
            stateHolder.updateUserState(
                userData = resultContent.toUI()
            )
        }
    }

    private suspend fun fetchBooksExplore() {
        val request = ExploreRequest.BooksWithCategories(state.value.categories)
        val result = fetchBooksExploreUseCase(request)
        val resultContent = result.getOrNull()
        if (result.isSuccess && resultContent != null) {
            stateHolder.updateBooksState(
                books = ExploreUiModel(
                    resultContent.items.map {
                        it.toUI {
                            _events.trySend(ExploreViewModelEvents.OpenBookDetails(it.id))
                        }
                    }
                )
            )
        }
    }

    private suspend fun searchBooksExplore() {
        val request = ExploreRequest.BooksWithSearchTerms(state.value.searchTerms)
        val result = if (state.value.searchTerms == "")
                        fetchBooksExploreUseCase(request)
                    else
                        searchBooksUseCase(request)
        val resultContent = result.getOrNull()
        if (result.isSuccess && resultContent != null) {
            stateHolder.updateBooksState(
                books = ExploreUiModel(
                    resultContent.items.map {
                        it.toUI {
                            _events.trySend(ExploreViewModelEvents.OpenBookDetails(it.id))
                        }
                    }
                )
            )
        }
    }

    private fun observeStateHolderEvents() {
        stateHolder.events.onEach { event ->
            when (event) {
                is ExploreStateHolderEvents.SearchBooks -> {
                    searchBooksExplore()
                }
                is ExploreStateHolderEvents.RefreshScreen -> {
                    fetchBooksExplore()
                }
            }
        }.launchIn(viewModelScope)
    }
}