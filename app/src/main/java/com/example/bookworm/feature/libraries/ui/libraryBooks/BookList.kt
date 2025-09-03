package com.example.bookworm.feature.libraries.ui.libraryBooks

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.example.bookworm.feature.books.domain.model.BookItem
import com.example.bookworm.feature.libraries.ui.LibraryViewModel
import com.example.bookworm.feature.libraries.domain.model.Shelf


@Composable
fun BookList(
    libraryViewModel: LibraryViewModel,
    libraryId: Int,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val uiState by libraryViewModel.uiState.collectAsState()
    var filteredLibrary: Shelf? by rememberSaveable { mutableStateOf(null) }
    var books: List<BookItem>? by rememberSaveable { mutableStateOf(null) }

    LaunchedEffect(uiState) {
        filteredLibrary = uiState.libraries?.firstOrNull{ it.id == libraryId }
        books = uiState.books
        if (uiState.modified) {
            Toast.makeText(context,
                context.getString(R.string.books_removed_successfully), Toast.LENGTH_SHORT).show()
            libraryViewModel.resetModifyState()
        }
        else if (uiState.errorMessage != null) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            LoadingIndicator()
        }
        else {
            if (filteredLibrary != null) {
                BookListTopBar(
                    navController = navController,
                    library = filteredLibrary!!
                )
                Text(
                    text = stringResource(R.string.books, filteredLibrary!!.volumeCount),
                    style = MaterialTheme.typography.titleMedium
                )
                if (books != null) {
                    BooksListColumn(
                        books = books!!,
                        onItemDismissed = { dismissedItem ->
                            libraryViewModel.removeBookFromShelf(
                                    shelfId = filteredLibrary!!.id,
                                    volumeId = dismissedItem.id
                            )
                            libraryViewModel.fetchLibraries()
                            libraryViewModel.getLibraryBooks(filteredLibrary!!.id)
                        },
                        library = filteredLibrary!!,
                        onClearLibrary = {
                            libraryViewModel.removeAllBooksFromShelf(shelfId = filteredLibrary!!.id)
                            libraryViewModel.fetchLibraries()
                            libraryViewModel.getLibraryBooks(filteredLibrary!!.id)
                        },
                        navController = navController
                    )
                }
                else {
                    Text(uiState.errorMessage ?: "")
                }
            }
            else {
                Text(uiState.errorMessage ?: "")
            }
        }
    }
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
