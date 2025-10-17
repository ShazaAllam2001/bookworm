package com.example.bookworm.modules.explore.presentation.ui.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalFocusManager
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.modules.book_grid.presentation.ui.BookGrid
import com.example.bookworm.modules.explore.presentation.model.ExploreUiState
import com.example.bookworm.ui.theme.dimens


@Composable
fun ExploreContent(state: ExploreUiState) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SearchField(
            state = state,
            searchText = searchText,
            onChangeText = { searchText = it }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium2),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.explore),
            style = MaterialTheme.typography.titleMedium
        )

        if (state.isLoading) {
            LoadingIndicator()
        }
        else if (state.books != null) {
            val books = state.books.items
            if (books.isNotEmpty()) {
                BookGrid(
                    bookList = books
                )
            }
        }
        else {
            Text(state.errorMessage ?: "")
        }
    }
}

@Composable
fun SearchField(
    state: ExploreUiState,
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
                onSearch = {
                    state.onSearchClicked(searchText)
                    focusManager.clearFocus()
                }
            )
        )
    }
}
