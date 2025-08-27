package com.example.bookworm.activities.main.modules.viewModel.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.BuildConfig
import com.example.bookworm.activities.main.modules.network.BooksApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale


private const val KEY = BuildConfig.API_KEY

class BookModel(
    val appLocale: Locale = Locale("en")
) : ViewModel() {

    var searchText by mutableStateOf("")

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    private var _bookIdUiState: BookIdUiState by mutableStateOf(BookIdUiState.Loading)
    val bookIdUiState: BookIdUiState get() = _bookIdUiState


    fun fetchBooksForYou(categories: List<String>) {
        viewModelScope.launch {
            try {
                var query = ""
                categories.forEachIndexed { index, category ->
                    query += "$category+subject"
                    if (categories.lastIndex != index)
                        query += "|"
                }
                val listResult = BooksApi.retrofitService.getBooks(
                    searchTerms = query,
                    lang = appLocale.language,
                    maxResults = 40,
                    apiKey = KEY)
                _booksUiState = BooksUiState.Success(listResult.items)
            } catch (e: IOException) {
                _booksUiState = BooksUiState.Error(e.message)
            } catch (e: HttpException) {
                _booksUiState = BooksUiState.Error(e.message)
            }
        }
    }

    fun searchBooks(searchTerms: String) {
        viewModelScope.launch {
            try {
                _booksUiState = BooksUiState.Loading
                val listResult = BooksApi.retrofitService.getBooks(
                    searchTerms = searchTerms,
                    lang = appLocale.language,
                    apiKey = KEY)
                _booksUiState = BooksUiState.Success(listResult.items)
            } catch (e: IOException) {
                _booksUiState = BooksUiState.Error(e.message)
            } catch (e: HttpException) {
                _booksUiState = BooksUiState.Error(e.message)
            }
        }
    }

    fun searchBookById(bookId: String) {
        viewModelScope.launch {
            try {
                _bookIdUiState = BookIdUiState.Loading
                val result = BooksApi.retrofitService.getBookById(
                    bookId = bookId,
                    apiKey = KEY)
                _bookIdUiState = BookIdUiState.Success(result)
            } catch (e: IOException) {
                _bookIdUiState= BookIdUiState.Error(e.message)
            } catch (e: HttpException) {
                _bookIdUiState= BookIdUiState.Error(e.message)
            }
        }
    }
}
