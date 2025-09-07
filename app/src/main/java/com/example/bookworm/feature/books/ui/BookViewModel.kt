package com.example.bookworm.feature.books.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.domain.model.BooksResult
import kotlinx.coroutines.launch
import com.example.bookworm.feature.books.domain.usecase.FetchBooksForYouUseCase
import com.example.bookworm.feature.books.domain.usecase.SearchBookByIdUseCase
import com.example.bookworm.feature.books.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val fetchBooksForYouUseCase: FetchBooksForYouUseCase,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val searchBookByIdUseCase: SearchBookByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    fun fetchBooksForYou(categories: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = fetchBooksForYouUseCase(categories)) {
                is BooksResult.Success -> {
                    _uiState.value = BookUiState(
                        books = result.message
                    )
                }
                is BooksResult.Error -> {
                    _uiState.value = BookUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun searchBooks(searchTerms: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = searchBooksUseCase(searchTerms)) {
                is BooksResult.Success -> {
                    _uiState.value = BookUiState(
                        books = result.message
                    )
                }
                is BooksResult.Error -> {
                    _uiState.value = BookUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun searchBookById(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = searchBookByIdUseCase(bookId)) {
                is BookIdResult.Success -> {
                    _uiState.value = BookUiState(
                        book = result.message
                    )
                }
                is BookIdResult.Error -> {
                    _uiState.value = BookUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }
}