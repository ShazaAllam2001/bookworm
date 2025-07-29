package com.example.bookworm.modules.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.modules.bookGrid.data.BookInfo
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.bookGrid.data.bookListAR
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class RecommendBookModel(val appLocale: Locale = Locale("en")) : ViewModel() {

    private val _books = MutableStateFlow<List<BookInfo>>(emptyList())
    val books: StateFlow<List<BookInfo>> get() = _books

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            delay(2000) // Simulate network call
            _books.value = bookList
            if (appLocale.language == "ar")
                _books.value = bookListAR
            _isLoading.value = false
        }
    }
}
