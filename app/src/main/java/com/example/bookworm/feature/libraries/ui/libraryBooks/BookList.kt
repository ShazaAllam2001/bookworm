package com.example.bookworm.feature.libraries.ui.libraryBooks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.feature.libraries.ui.LibraryViewModel
import com.example.bookworm.feature.libraries.domain.model.Shelf


@Composable
fun BookList(
    libraryViewModel: LibraryViewModel,
    libraryId: Int,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    /*LaunchedEffect(libraryViewModel.libraryRemoveUiState) {
        when (libraryViewModel.libraryRemoveUiState) {
            is LibraryRemoveUiState.Success ->
                Toast.makeText(context,
                    context.getString(R.string.books_removed_successfully), Toast.LENGTH_SHORT).show()
            is LibraryRemoveUiState.Error ->
                Toast.makeText(context, (libraryViewModel.libraryRemoveUiState as LibraryRemoveUiState.Error).msg, Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (libraryViewModel.librariesUiState) {
            is LibrariesUiState.Loading ->
                LoadingIndicator()
            is LibrariesUiState.Success -> {
                var libraries = (libraryViewModel.librariesUiState as LibrariesUiState.Success).msg
                libraries = libraries.filter { it.id == libraryId }
                if (libraries.size == 1) {
                    BookListTopBar(
                        navController = navController,
                        library = libraries[0]
                    )
                    when (libraryViewModel.booksUiState) {
                        is BooksUiState.Loading ->
                            LoadingIndicator()
                        is BooksUiState.Success -> {
                            val books = (libraryViewModel.booksUiState as BooksUiState.Success).msg
                            Text(
                                text = stringResource(R.string.books, libraries[0].volumeCount),
                                style = MaterialTheme.typography.titleMedium
                            )
                            BooksListColumn(
                                books = books,
                                onItemDismissed = { dismissedItem ->
                                    libraryViewModel.removeBookFromShelf(shelfId = libraries[0].id, volumeId = dismissedItem.id)
                                },
                                library = libraries[0],
                                onClearLibrary = { library ->
                                    libraryViewModel.removeAllBooksFromShelf(shelfId = library.id)
                                },
                                navController = navController
                            )
                        }
                        is BooksUiState.Error ->
                            Text((libraryViewModel.booksUiState as BooksUiState.Error).msg ?: "")
                    }
                }
            }
            is LibrariesUiState.Error ->
                Text((libraryViewModel.librariesUiState as LibrariesUiState.Error).msg ?: "")
        }
    }*/
}

@Composable
fun BookListTopBar(
    library: Shelf,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier.padding(15.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_64dp),
                    contentDescription = "Back to libraries"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = library.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
