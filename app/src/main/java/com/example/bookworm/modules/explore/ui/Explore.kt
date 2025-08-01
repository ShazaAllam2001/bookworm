package com.example.bookworm.modules.explore.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.ui.BookGrid
import com.example.bookworm.modules.viewModel.BookModel
import com.example.bookworm.modules.viewModel.BooksUiState
import com.example.bookworm.modules.network.LoadingIndicator
import com.example.bookworm.modules.viewModel.BookIdUiState


@Composable
fun Explore(
    viewModel: BookModel = BookModel(),
    navController: NavHostController = rememberNavController()
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.explore),
            style = MaterialTheme.typography.titleLarge
        )
        SearchField(
            viewModel = viewModel,
            searchText = searchText,
            onChangeText = { searchText = it }
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

@Composable
fun SearchField(
    viewModel: BookModel = BookModel(),
    searchText: String,
    onChangeText: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = searchText,
            onValueChange = onChangeText,
            placeholder = {
                Text(
                    stringResource(R.string.search_for_books),
                    style = MaterialTheme.typography.titleSmall
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15.dp)
        )
        IconButton(
            onClick = { viewModel.searchBooks(searchText) }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = "Search Button"
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ExplorePreview() {
    Explore()
}
