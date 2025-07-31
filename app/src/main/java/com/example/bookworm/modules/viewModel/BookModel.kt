package com.example.bookworm.modules.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.BuildConfig
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.bookGrid.data.bookListAR
import com.example.bookworm.modules.network.BooksApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import java.util.Locale


private const val KEY = BuildConfig.API_KEY

class BookModel(val appLocale: Locale = Locale("en")) : ViewModel() {

    /*private val _books = MutableStateFlow<List<BookInfo>>(emptyList())
    val books: StateFlow<List<BookInfo>> get() = _books

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading*/

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            // call books API
            try {
                val listResult = BooksApi.retrofitService.getBooks(
                    searchTerms = "flowers+intitle",
                    maxResults = 40,
                    lang = appLocale.language,
                    apiKey = KEY)
                _booksUiState = BooksUiState.Success(listResult.items)
            } catch (e: IOException) {
                _booksUiState = BooksUiState.Error(e.message)
            }

            /*_books.value = bookList
            if (appLocale.language == "ar")
                _books.value = bookListAR
            _isLoading.value = false*/
        }
    }
}
