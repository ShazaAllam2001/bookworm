package com.example.bookworm.feature.libraries.ui.composables.libraryBooks

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.libraries.ui.viewModel.LibraryViewModel
import com.example.bookworm.feature.libraries.data.model.Shelf
import com.example.bookworm.ui.theme.dimens


@Composable
fun BookList(
    libraryViewModel: LibraryViewModel,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
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
            if (uiState.library != null) {
                BookListTopBar(
                    navController = navController,
                    library = uiState.library!!
                )
                Text(
                    text = stringResource(R.string.books, uiState.library!!.volumeCount),
                    style = MaterialTheme.typography.titleMedium
                )
                if (uiState.books != null) {
                    BooksListColumn(
                        books = uiState.books!!,
                        onItemDismissed = { dismissedItem ->
                            libraryViewModel.removeBookFromShelf(
                                shelfId = uiState.library!!.id,
                                volumeId = dismissedItem.id
                            )
                        },
                        library = uiState.library!!,
                        onClearLibrary = {
                            libraryViewModel.removeAllBooksFromShelf(shelfId = uiState.library!!.id)
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
        modifier = Modifier.padding(MaterialTheme.dimens.paddingMedium2),
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
