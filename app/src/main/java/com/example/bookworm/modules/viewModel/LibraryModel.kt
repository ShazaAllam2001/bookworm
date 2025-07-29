package com.example.bookworm.modules.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.modules.bookGrid.data.BookInfo
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.bookGrid.data.bookListAR
import com.example.bookworm.modules.myLibrary.data.LibraryInfo
import com.example.bookworm.modules.myLibrary.data.librariesList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class LibraryModel(
    val appLocale: Locale = Locale("en")
) : ViewModel() {

    private val _libraries = MutableStateFlow<List<LibraryInfo>>(emptyList())
    val libraries: StateFlow<List<LibraryInfo>> get() = _libraries

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchLibraries()
    }

    private fun fetchLibraries() {
        viewModelScope.launch {
            delay(2000) // Simulate network call
            _libraries.value = librariesList
            _isLoading.value = false
        }
    }
}
