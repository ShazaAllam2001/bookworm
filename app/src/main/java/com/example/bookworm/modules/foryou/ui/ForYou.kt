package com.example.bookworm.modules.foryou.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.ui.BookGrid
import com.example.bookworm.modules.viewModel.BookModel
import com.example.bookworm.modules.viewModel.BooksUiState
import com.example.bookworm.modules.network.LoadingIndicator


@Composable
fun ForYou(
    viewModel: BookModel = BookModel(),
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.for_you),
            style = MaterialTheme.typography.titleLarge
        )
        when (viewModel.booksUiState) {
            is BooksUiState.Loading ->
                LoadingIndicator()
            is BooksUiState.Success ->
                BookGrid(
                    navController = navController,
                    bookList = (viewModel.booksUiState as BooksUiState.Success).msg
                )
            is BooksUiState.Error ->
                Text((viewModel.booksUiState as BooksUiState.Error).msg ?: "")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ForYouPreview() {
    ForYou()
}
