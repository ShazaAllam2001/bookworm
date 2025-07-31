package com.example.bookworm.modules.bookGrid.ui.components

import android.content.res.Configuration
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.viewModel.BookItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.example.bookworm.modules.viewModel.BookModel
import com.example.bookworm.modules.viewModel.BooksUiState
import com.example.bookworm.modules.viewModel.LoadingIndicator


@Composable
fun BookDetails(
    bookViewModel: BookModel = BookModel(),
    bookId: Int,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (bookViewModel.booksUiState) {
            is BooksUiState.Loading ->
                LoadingIndicator()
            is BooksUiState.Success -> {
                val books = (bookViewModel.booksUiState as BooksUiState.Success).msg
                BookTopBar(
                    navController = navController,
                    bookTitle = books[bookId].volumeInfo.title
                )
                BookInfo(book = books[bookId])
            }
            is BooksUiState.Error ->
                Text((bookViewModel.booksUiState as BooksUiState.Error).msg ?: "")
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = bookTitle,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun BookInfo(book: BookItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier
                .fillMaxWidth()
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

            }
        ) {
            Text(stringResource(R.string.add_to_shelf))
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "ar"
)
@Composable
fun BookDetailsPreview() {
    BookDetails(bookId = 0)
}
