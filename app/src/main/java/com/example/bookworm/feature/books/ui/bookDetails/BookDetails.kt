package com.example.bookworm.feature.books.ui.bookDetails

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.example.bookworm.feature.books.ui.BookViewModel
import com.example.bookworm.feature.libraries.ui.LibraryViewModel


@Composable
fun BookDetails(
    bookViewModel: BookViewModel,
    libraryViewModel: LibraryViewModel,
    updateLibrary: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    val uiState by bookViewModel.uiState.collectAsState()
    val libraryUiState by libraryViewModel.uiState.collectAsState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(libraryUiState) {
        if (libraryUiState.modified) {
            Toast.makeText(context,
                context.getString(R.string.book_added_successfully), Toast.LENGTH_SHORT).show()
            libraryViewModel.resetModifyState()
        }
        else if (uiState.errorMessage != null) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            LoadingIndicator()
        }
        else if (uiState.book != null) {
            val book = uiState.book
            BookTopBar(
                navController = navController,
                bookTitle = book!!.volumeInfo.title
            )
            BookInfo(
                book = book,
                onShowDialogChange = { showDialog = it }
            )

            if (showDialog) {
                AddToShelf(
                    onDismiss = { showDialog = false },
                    onAdd = { checkedShelves ->
                        checkedShelves.forEach { (id, checked) ->
                            if (checked) {
                                libraryViewModel.addBookToShelf(shelfId = id, volumeId = book.id)
                            }
                        }
                        updateLibrary()
                    },
                    libraryViewModel = libraryViewModel
                )
            }
        }
        else {
            Text(uiState.errorMessage ?: "")
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
