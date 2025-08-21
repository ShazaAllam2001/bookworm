package com.example.bookworm.activities.main.modules.ui.foryou

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.ui.bookGrid.BookGrid
import com.example.bookworm.activities.main.modules.viewModel.books.BookModel
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.sharedPref.viewModel.PrefViewModel


@Composable
fun ForYou(
    viewModel: BookModel,
    prefViewModel: PrefViewModel,
    navController: NavHostController = rememberNavController()
) {
    var name by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val user = prefViewModel.readPreferences()
        name = user.displayName
    }

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
            text = stringResource(R.string.for_name, name),
            style = MaterialTheme.typography.titleLarge
        )
        when (viewModel.booksUiState) {
            is BooksUiState.Loading ->
                LoadingIndicator()
            is BooksUiState.Success -> {
                val books = (viewModel.booksUiState as BooksUiState.Success).msg
                if (books.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        text = stringResource(R.string.pick_your_preferences_from_settings),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                else {
                    BookGrid(
                        navController = navController,
                        bookList = books
                    )
                }
            }
            is BooksUiState.Error ->
                Text((viewModel.booksUiState as BooksUiState.Error).msg ?: "")
        }
    }
}
