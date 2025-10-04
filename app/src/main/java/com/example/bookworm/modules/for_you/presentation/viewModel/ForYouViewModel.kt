package com.example.bookworm.modules.for_you.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.modules.for_you.domain.model.toUI
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import com.example.bookworm.modules.for_you.domain.usecase.FetchBooksForYouUseCase
import com.example.bookworm.modules.for_you.presentation.model.ForYouUiModel
import com.example.bookworm.modules.for_you.presentation.state.ForYouStateHolder
import com.example.bookworm.modules.for_you.presentation.state.events.ForYouStateHolderEvents
import com.example.bookworm.modules.for_you.presentation.viewModel.events.ForYouViewModelEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val fetchBooksForYouUseCase: FetchBooksForYouUseCase,
    private val stateHolder: ForYouStateHolder
): ViewModel() {

    private val _events: Channel<ForYouViewModelEvents> = Channel(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()

    val state = stateHolder.state

    init {
        viewModelScope.launch {
            fetchBooksForYou()
            observeStateHolderEvents()
        }
    }

    private suspend fun fetchBooksForYou() {
        val request = ForYouRequest.BooksWithCategories(listOf("Cooking", "Playing"))
        val result = fetchBooksForYouUseCase(request)
        val resultContent = result.getOrNull()
        if (result.isSuccess && resultContent != null) {
            stateHolder.updateBooksState(
                books = ForYouUiModel(
                    resultContent.items.map {
                        it.toUI {
                            _events.trySend(ForYouViewModelEvents.OpenBookDetails(it.id))
                        }
                    }
                )
            )
        }
    }

    private fun observeStateHolderEvents() {
        stateHolder.events.onEach { event ->
            when (event) {
                is ForYouStateHolderEvents.RefreshScreen -> {
                    fetchBooksForYou()
                }
            }
        }.launchIn(viewModelScope)
    }
}