package com.example.bookworm.feature.libraries.ui

import androidx.lifecycle.ViewModel
import java.util.Locale

//private const val KEY = BuildConfig.API_KEY

class LibraryViewModel(
    val appLocale: Locale = Locale("en"),
    //val prefRepo: PrefRepo
) : ViewModel() {

    /*private var _librariesUiState: LibrariesUiState by mutableStateOf(LibrariesUiState.Loading)
    val librariesUiState: LibrariesUiState get() = _librariesUiState

    private var _booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    val booksUiState: BooksUiState get() = _booksUiState

    private var _libraryAddUiState: LibraryAddUiState by mutableStateOf(LibraryAddUiState.Loading)
    val libraryAddUiState: LibraryAddUiState get() = _libraryAddUiState

    private var _libraryRemoveUiState: LibraryRemoveUiState by mutableStateOf(LibraryRemoveUiState.Loading)
    val libraryRemoveUiState: LibraryRemoveUiState get() = _libraryRemoveUiState*/

    /*init {
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
                _librariesUiState = LibrariesUiState.Success(listResult.items)
            } catch (e: IOException) {
                _librariesUiState = LibrariesUiState.Error(e.message)
            } catch (e: HttpException) {
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
             } catch (e: HttpException) {
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
            } catch (e: HttpException) {
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
            } catch (e: HttpException) {
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
            } catch (e: HttpException) {
                _libraryRemoveUiState = LibraryRemoveUiState.Error(e.message)
            }
        }
    }*/

}