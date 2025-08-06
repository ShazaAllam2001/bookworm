package com.example.bookworm.activities.main.modules.myLibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.viewModel.books.BookItem
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.activities.main.modules.network.LoadingIndicator
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.Shelf


@Composable
fun BookList(
    libraryViewModel: LibraryModel = LibraryModel(),
    libraryId: Int,
    navController: NavHostController = rememberNavController()
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val libraries = (libraryViewModel.librariesUiState as LibrariesUiState.Success).msg
        libraries.filter { it.id == libraryId }
        if (libraries.size == 1) {
            BookListTopBar(
                navController = navController,
                libraryTitle = libraries[0].title
            )
            when (libraryViewModel.booksUiState) {
                is BooksUiState.Loading ->
                    LoadingIndicator()
                is BooksUiState.Success -> {
                    val books = (libraryViewModel.booksUiState as BooksUiState.Success).msg
                    Books(
                        library = libraries[0],
                        books = books,
                        navController = navController
                    )
                }
                is BooksUiState.Error ->
                    Text((libraryViewModel.booksUiState as BooksUiState.Error).msg ?: "")
            }
        }
    }
}

@Composable
fun BookListTopBar(
    navController: NavHostController,
    libraryTitle: String
) {
    Surface(
        modifier = Modifier.padding(15.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_64dp),
                    contentDescription = "Back to libraries"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = libraryTitle,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun Books(
    library: Shelf,
    books: List<BookItem>,
    navController: NavHostController
) {
    Text(
        stringResource(R.string.books, library.volumeCount),
        style = MaterialTheme.typography.titleMedium
    )
    LazyColumn(
        modifier = Modifier.padding(15.dp),
    ) {
        items(library.volumeCount) { index ->
            BookListRow(
                navController = navController,
                book = books[index]
            )
        }
    }
}

