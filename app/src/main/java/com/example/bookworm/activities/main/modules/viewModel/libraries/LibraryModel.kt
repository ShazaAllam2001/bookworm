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

    private var _librariesUiState: LibrariesUiState by mutableStateOf(LibrariesUiState.Loading)
    val librariesUiState: LibrariesUiState get() = _librariesUiState

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    private var _libraryAddUiState: LibraryAddUiState by mutableStateOf(LibraryAddUiState.Loading)
    val libraryAddUiState: LibraryAddUiState get() = _libraryAddUiState

    private var _libraryRemoveUiState: LibraryRemoveUiState by mutableStateOf(LibraryRemoveUiState.Loading)
    val libraryRemoveUiState: LibraryRemoveUiState get() = _libraryRemoveUiState

    init {
        fetchLibraries()
    }

    fun fetchLibraries() {
        viewModelScope.launch {
            try {
                _librariesUiState = LibrariesUiState.Loading
                val token = prefRepo.readPreferences().token
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

     fun getLibraryBooks(shelfId: Int) {
         viewModelScope.launch {
             try {
                 _booksUiState = BooksUiState.Loading
                 val token = prefRepo.readPreferences().token
                 val listResult = BooksApi.retrofitService.getShelfBooks(
                     shelfId = shelfId,
                     token = "Bearer $token",
                     apiKey = KEY
                 )
                 _booksUiState = BooksUiState.Success(listResult.body()?.items ?: emptyList())
             } catch (e: IOException) {
                 _booksUiState = BooksUiState.Error(e.message)
             }
         }
     }

    fun addBookToShelf(shelfId: Int, volumeId: String) {
        viewModelScope.launch {
            try {
                _libraryAddUiState = LibraryAddUiState.Loading
                val token = prefRepo.readPreferences().token
                BooksApi.retrofitService.addBookToShelf(
                    shelfId = shelfId,
                    volumeId = volumeId,
                    token = "Bearer $token",
                    apiKey = KEY
                )
                _libraryAddUiState = LibraryAddUiState.Success
            } catch (e: IOException) {
                _libraryAddUiState = LibraryAddUiState.Error(e.message)
            }
        }
    }

    fun removeBookFromShelf(shelfId: Int, volumeId: String) {
        viewModelScope.launch {
            try {
                _libraryRemoveUiState = LibraryRemoveUiState.Loading
                val token = prefRepo.readPreferences().token
                BooksApi.retrofitService.removeBookFromShelf(
                    shelfId = shelfId,
                    volumeId = volumeId,
                    token = "Bearer $token",
                    apiKey = KEY
                )
                fetchLibraries()
                getLibraryBooks(shelfId = shelfId)
                _libraryRemoveUiState = LibraryRemoveUiState.Success
            } catch (e: IOException) {
                _libraryRemoveUiState = LibraryRemoveUiState.Error(e.message)
            }
        }
    }

    fun removeAllBooksFromShelf(shelfId: Int) {
        viewModelScope.launch {
            try {
                _libraryRemoveUiState = LibraryRemoveUiState.Loading
                val token = prefRepo.readPreferences().token
                BooksApi.retrofitService.removeAllBooksFromShelf(
                    shelfId = shelfId,
                    token = "Bearer $token",
                    apiKey = KEY
                )
                fetchLibraries()
                getLibraryBooks(shelfId = shelfId)
                _libraryRemoveUiState = LibraryRemoveUiState.Success
            } catch (e: IOException) {
                _libraryRemoveUiState = LibraryRemoveUiState.Error(e.message)
            }
        }
    }

}
