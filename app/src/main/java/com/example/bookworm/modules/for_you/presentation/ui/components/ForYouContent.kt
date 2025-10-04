package com.example.bookworm.modules.for_you.presentation.ui.components

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.modules.book_grid.presentation.ui.BookGrid
import com.example.bookworm.modules.for_you.presentation.model.ForYouUiState
import com.example.bookworm.ui.theme.dimens

@Composable
fun ForYouContent(state: ForYouUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium2),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = buildAnnotatedString {
                append(stringResource(R.string.for_name))
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(" ${state.name}")
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
        if (state.isLoading) {
            LoadingIndicator()
        }
        else if (state.books != null) {
            if (state.books.items.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.dimens.paddingMedium2),
                    text = stringResource(R.string.pick_your_preferences_from_settings),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            else {
                BookGrid(
                    bookList = state.books.items
                )
            }
        }
        else {
            Text(state.errorMessage ?: "")
        }
    }
}