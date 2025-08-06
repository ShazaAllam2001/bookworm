package com.example.bookworm.activities.main.modules.viewModel.libraries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.BuildConfig
import com.example.bookworm.activities.main.modules.network.BooksApi
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale


private const val KEY = BuildConfig.API_KEY

class LibraryModel(
    val appLocale: Locale = Locale("en"),
    val token: String = ""
) : ViewModel() {

    private var _librariesUiState: LibrariesUiState by mutableStateOf(LibrariesUiState.Loading)
    val librariesUiState: LibrariesUiState get() = _librariesUiState

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    init {
        fetchLibraries()
    }

    private fun fetchLibraries() {
        viewModelScope.launch {
            try {
                val listResult = BooksApi.retrofitService.getMyBookshelves(
                    token = token,
                    apiKey = KEY
                )
                _librariesUiState = LibrariesUiState.Success(listResult.items)
            } catch (e: IOException) {
                _librariesUiState = LibrariesUiState.Error(e.message)
            }
        }
    }

     fun getLibraryBooks(libraryId: Int) {
         viewModelScope.launch {
             try {
                 val listResult = BooksApi.retrofitService.getShelfBooks(
                     shelfId = libraryId,
                     token = token,
                     apiKey = KEY
                 )
                 _booksUiState = BooksUiState.Success(listResult.items)
             } catch (e: IOException) {
                 _booksUiState = BooksUiState.Error(e.message)
             }
         }
     }
}
