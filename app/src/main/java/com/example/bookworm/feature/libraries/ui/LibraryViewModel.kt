package com.example.bookworm.feature.libraries.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.usecase.AddBookToShelfUseCase
import com.example.bookworm.feature.libraries.domain.usecase.FetchLibrariesUseCase
import com.example.bookworm.feature.libraries.domain.usecase.GetLibraryBooksUseCase
import com.example.bookworm.feature.libraries.domain.usecase.RemoveAllBooksFromShelfUseCase
import com.example.bookworm.feature.libraries.domain.usecase.RemoveBookFromShelfUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val fetchLibrariesUseCase: FetchLibrariesUseCase,
    private val getLibraryBooksUseCase: GetLibraryBooksUseCase,
    private val addBookToShelfUseCase: AddBookToShelfUseCase,
    private val removeBookFromShelfUseCase: RemoveBookFromShelfUseCase,
    private val removeAllBooksFromShelfUseCase: RemoveAllBooksFromShelfUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    init {
        fetchLibraries()
    }

    fun fetchLibraries(shelfId: Int? = null) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = fetchLibrariesUseCase()) {
                is LibraryResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        libraries = result.message,
                        library = result.message.firstOrNull{ it.id == shelfId }
                    )
                }
                is LibraryResult.Error -> {
                    _uiState.value = LibraryUiState(
                        errorMessage = result.message
                    )
                }
                LibraryResult.Loading -> { }
            }
        }
    }

     fun getLibraryBooks(shelfId: Int) {
         viewModelScope.launch {
             _uiState.value = _uiState.value.copy(isLoading = true)

             when (val result = getLibraryBooksUseCase(shelfId)) {
                 is BooksResult.Success -> {
                     _uiState.value = _uiState.value.copy(
                         isLoading = false,
                         books = result.message
                     )
                 }
                 is BooksResult.Error -> {
                     _uiState.value = LibraryUiState(
                         errorMessage = result.message
                     )
                 }
                 BooksResult.Loading -> { }
             }
         }
     }

    fun addBookToShelf(shelfId: Int, volumeId: String) {
        viewModelScope.launch {
            when (val result = addBookToShelfUseCase(shelfId, volumeId)) {
                is ModifyLibraryResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        modified = result.message
                    )
                }
                is ModifyLibraryResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = result.message
                    )
                }
                ModifyLibraryResult.Loading -> { }
            }
        }
    }

    fun removeBookFromShelf(shelfId: Int, volumeId: String) {
        viewModelScope.launch {
            val result = removeBookFromShelfUseCase(shelfId, volumeId)
            val resultLibraries = fetchLibrariesUseCase()
            val resultBooks = getLibraryBooksUseCase(shelfId)
            if (result is ModifyLibraryResult.Success
                && resultLibraries is LibraryResult.Success
                && resultBooks is BooksResult.Success) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    modified = result.message,
                    libraries = resultLibraries.message,
                    library = resultLibraries.message.firstOrNull{ it.id == shelfId },
                    books = resultBooks.message
                )
            }
            else if (result is ModifyLibraryResult.Error) {
                _uiState.value = LibraryUiState(
                    errorMessage = result.message
                )
            }
            else if (resultLibraries is LibraryResult.Error) {
                _uiState.value = LibraryUiState(
                    errorMessage = resultLibraries.message
                )
            }
            else if (resultBooks is BooksResult.Error) {
                _uiState.value = LibraryUiState(
                    errorMessage = resultBooks.message
                )
            }
        }
    }

    fun removeAllBooksFromShelf(shelfId: Int) {
        viewModelScope.launch {
            val result = removeAllBooksFromShelfUseCase(shelfId)
            val resultLibraries = fetchLibrariesUseCase()
            if (result is ModifyLibraryResult.Success
                && resultLibraries is LibraryResult.Success) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    modified = result.message,
                    libraries = resultLibraries.message,
                    library = resultLibraries.message.firstOrNull{ it.id == shelfId },
                    books = emptyList()
                )
            }
            else if (result is ModifyLibraryResult.Error) {
                _uiState.value = LibraryUiState(
                    errorMessage = result.message
                )
            }
            else if (resultLibraries is LibraryResult.Error) {
                _uiState.value = LibraryUiState(
                    errorMessage = resultLibraries.message
                )
            }
        }
    }

    fun resetModifyState() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                modified = false
            )
        }
    }
}