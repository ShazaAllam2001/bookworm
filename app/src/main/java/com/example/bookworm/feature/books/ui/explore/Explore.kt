package com.example.bookworm.feature.books.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookworm.R
import com.example.bookworm.feature.books.ui.bookGrid.BookGrid
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.example.bookworm.feature.auth.ui.loggedin.LoggedInViewModel
import com.example.bookworm.feature.books.ui.BookViewModel
import com.example.bookworm.ui.theme.dimens


@Composable
fun Explore(
    bookViewModel: BookViewModel,
    loggedInViewModel: LoggedInViewModel,
    navController: NavHostController = rememberNavController()
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    val uiState by bookViewModel.uiState.collectAsStateWithLifecycle()
    val userUiState by loggedInViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userUiState) {
        if (userUiState.userData == null) {
            loggedInViewModel.getUserData()
        }
        else {
            bookViewModel.fetchBooksForYou(userUiState.userData!!.categories)
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
            categories = userUiState.userData!!.categories,
            searchText = searchText,
            onChangeText = { searchText = it }
        )
        if (uiState.isLoading) {
            LoadingIndicator()
        }
        else if (uiState.books != null) {
            val books = uiState.books
            if (books!!.isNotEmpty()) {
                BookGrid(
                    navController = navController,
                    bookList = books
                )
            }
        }
        else {
            Text(uiState.errorMessage ?: "")
        }
    }
}

@Composable
fun SearchField(
    bookViewModel: BookViewModel,
    categories: List<String>,
    searchText: String,
    onChangeText: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.padding(MaterialTheme.dimens.paddingMedium)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = onChangeText,
            prefix = {
                Icon(
                    painter = painterResource(R.drawable.search_24),
                    contentDescription = "Search Button"
                )
            },
            placeholder = {
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.dimens.paddingExtraSmall),
                    text = stringResource(R.string.search_for_books),
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
                    focusManager.clearFocus()
                }
            )
        )
    }
}
