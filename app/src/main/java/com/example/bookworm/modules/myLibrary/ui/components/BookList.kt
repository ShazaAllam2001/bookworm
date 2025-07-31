package com.example.bookworm.modules.myLibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.myLibrary.data.LibraryInfo
import com.example.bookworm.modules.viewModel.BookItem
import com.example.bookworm.modules.viewModel.BookModel
import com.example.bookworm.modules.viewModel.BooksUiState
import com.example.bookworm.modules.viewModel.LibraryModel
import com.example.bookworm.modules.viewModel.LoadingIndicator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookList(
    bookViewModel: BookModel = BookModel(),
    libraryViewModel: LibraryModel = LibraryModel(),
    libraryId: Int,
    navController: NavHostController = rememberNavController()
) {
    val libraries by libraryViewModel.libraries.collectAsState()
    val isLibrariesLoading by libraryViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLibrariesLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            BookListTopBar(
                navController = navController,
                libraryTitle = stringResource(libraries[libraryId].name)
            )
            when (bookViewModel.booksUiState) {
                is BooksUiState.Loading ->
                    LoadingIndicator()
                is BooksUiState.Success -> {
                    val books = (bookViewModel.booksUiState as BooksUiState.Success).msg
                    Books(
                        library = libraries[libraryId],
                        books = books,
                        navController = navController
                    )
                }
                is BooksUiState.Error ->
                    Text((bookViewModel.booksUiState as BooksUiState.Error).msg ?: "")
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
    library: LibraryInfo,
    books: List<BookItem>,
    navController: NavHostController
) {
    Text(
        stringResource(R.string.books, library.numberOfBooks),
        style = MaterialTheme.typography.titleMedium
    )
    LazyColumn(
        modifier = Modifier.padding(15.dp),
    ) {
        items(library.numberOfBooks) { index ->
            BookRowCard(
                navController = navController,
                book = books[index]
            )
        }
    }
}

@Composable
fun BookRowCard(
    navController: NavHostController,
    book: BookItem
) {
    Card(
        onClick = {
            navController.navigate("books/${book.id}")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    if (book.volumeInfo.categories.isNullOrEmpty()) "" else book.volumeInfo.categories[0],
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    book.volumeInfo.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(R.string.by,
                        if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
                    ),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            CoilImage(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                imageModel = { book.volumeInfo.imageLinks?.smallThumbnail },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}