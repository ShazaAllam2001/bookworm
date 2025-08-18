package com.example.bookworm.activities.main.modules.ui.bookGrid.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.viewModel.books.BookItem
import com.example.bookworm.activities.main.modules.viewModel.books.BookModel
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.activities.main.modules.viewModel.books.BookIdUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun BookDetails(
    bookViewModel: BookModel,
    libraryViewModel: LibraryModel,
    navController: NavHostController = rememberNavController()
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (bookViewModel.bookIdUiState) {
            is BookIdUiState.Loading ->
                LoadingIndicator()
            is BookIdUiState.Success -> {
                val book = (bookViewModel.bookIdUiState as BookIdUiState.Success).msg
                BookTopBar(
                    navController = navController,
                    bookTitle = book.volumeInfo.title
                )
                BookInfo(
                    book = book,
                    onShowDialogChange = { showDialog = it }
                )

                if (showDialog) {
                    AddToShelf(
                       onDismiss = { showDialog = false },
                        onAdd = { shelfId ->
                            //libraryViewModel.
                        }
                    )
                }
            }
            is BookIdUiState.Error ->
                Text((bookViewModel.bookIdUiState as BookIdUiState.Error).msg ?: "")
        }
    }
}

@Composable
fun BookTopBar(
    navController: NavHostController,
    bookTitle: String
) {
    Surface(
        modifier = Modifier.padding(5.dp),
        color = MaterialTheme.colorScheme.onPrimary
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
                    contentDescription = "Back to books list"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                text = bookTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun BookInfo(
    book: BookItem,
    onShowDialogChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            imageModel = { book.volumeInfo.imageLinks?.thumbnail },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        Text(
            stringResource(R.string.by,
                if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
            ),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            book.volumeInfo.description?: "",
            style = MaterialTheme.typography.bodyMedium
        )
        TextButton(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
            ),
            onClick = {
                onShowDialogChange(true)
            }
        ) {
            Text(stringResource(R.string.add_to_shelf))
        }
    }
}
