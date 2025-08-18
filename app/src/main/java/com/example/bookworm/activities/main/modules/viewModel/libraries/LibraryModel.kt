package com.example.bookworm.activities.main.modules.viewModel.libraries

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.BuildConfig
import com.example.bookworm.activities.main.modules.network.BooksApi
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import com.example.bookworm.sharedPref.data.PrefRepo
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale


private const val KEY = BuildConfig.API_KEY

class LibraryModel(
    val appLocale: Locale = Locale("en"),
    val prefRepo: PrefRepo
) : ViewModel() {
    private lateinit var token: String

    private var _librariesUiState: LibrariesUiState by mutableStateOf(LibrariesUiState.Loading)
    val librariesUiState: LibrariesUiState get() = _librariesUiState

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    init {
        viewModelScope.launch {
            token = prefRepo.readPreferences().token
            Log.d("token", token)
            fetchLibraries()
        }
    }

    private fun fetchLibraries() {
        viewModelScope.launch {
            try {
                val listResult = BooksApi.retrofitService.getMyBookshelves(
                    token = "Bearer $token",
                    apiKey = KEY
                )
                Log.d("Libraries", listResult.toString())
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
                     token = "Bearer $token",
                     apiKey = KEY
                 )
                 _booksUiState = BooksUiState.Success(listResult.items)
             } catch (e: IOException) {
                 _booksUiState = BooksUiState.Error(e.message)
             }
         }
     }
}
