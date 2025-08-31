package com.example.bookworm.activities.main.modules.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import com.example.bookworm.R
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.example.bookworm.activities.main.modules.ui.bookGrid.BookGrid
import com.example.bookworm.activities.main.modules.viewModel.books.BookModel
import com.example.bookworm.activities.main.modules.viewModel.books.BooksUiState
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.ui.theme.dimens


@Composable
fun Explore(
    bookViewModel: BookModel,
    userViewModel: UserViewModel,
    navController: NavHostController = rememberNavController()
) {
    var categories: List<String> by rememberSaveable { mutableStateOf(emptyList()) }

    LaunchedEffect(userViewModel.userData) {
        val uid = userViewModel.readPreferences().uid
        if (userViewModel.userData == null) {
            userViewModel.readUser(uid)
        }
        else {
            categories = userViewModel.userData!!.categories
            bookViewModel.fetchBooksForYou(categories)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.explore),
            style = MaterialTheme.typography.titleLarge
        )
        SearchField(
            bookViewModel = bookViewModel,
            categories = categories,
            searchText = bookViewModel.searchText,
            onChangeText = { bookViewModel.searchText = it }
        )
        when (bookViewModel.booksUiState) {
            is BooksUiState.Loading ->
                LoadingIndicator()
            is BooksUiState.Success ->
                BookGrid(
                    navController = navController,
                    bookList = (bookViewModel.booksUiState as BooksUiState.Success).msg
                )
            is BooksUiState.Error ->
                Text((bookViewModel.booksUiState as BooksUiState.Error).msg ?: "")
        }
    }
}

@Composable
fun SearchField(
    bookViewModel: BookModel,
    categories: List<String>,
    searchText: String,
    onChangeText: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(MaterialTheme.dimens.paddingMedium),
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
            shape = RoundedCornerShape(MaterialTheme.dimens.roundCorner),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch =  {
                    if (searchText != "") {
                        bookViewModel.searchBooks(searchText)
                    }
                    else {
                        if (categories.isNotEmpty()) {
                            bookViewModel.fetchBooksForYou(categories)
                        }
                    }
                }
            )
        )
        IconButton(
            onClick = {
                if (searchText != "") {
                    bookViewModel.searchBooks(searchText)
                }
                else {
                    if (categories.isNotEmpty()) {
                        bookViewModel.fetchBooksForYou(categories)
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = "Search Button"
            )
        }
    }
}
