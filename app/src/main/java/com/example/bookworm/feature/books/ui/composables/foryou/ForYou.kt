package com.example.bookworm.feature.books.ui.composables.foryou

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.feature.books.ui.composables.bookGrid.BookGrid
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.user.ui.viewModel.LoggedInViewModel
import com.example.bookworm.feature.books.ui.viewModel.BookViewModel
import com.example.bookworm.ui.theme.dimens


@Composable
fun ForYou(
    bookViewModel: BookViewModel,
    loggedInViewModel: LoggedInViewModel,
    navController: NavHostController = rememberNavController()
) {
    var name by rememberSaveable { mutableStateOf("") }
    val uiState by bookViewModel.uiState.collectAsStateWithLifecycle()
    val userUiState by loggedInViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userUiState) {
        if (userUiState.userData == null) {
            loggedInViewModel.getUserData()
        }
        else {
            name = userUiState.userData!!.displayName
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
                .padding(MaterialTheme.dimens.paddingMedium2),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = buildAnnotatedString {
                append(stringResource(R.string.for_name))
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(" $name")
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
        if (uiState.isLoading) {
            LoadingIndicator()
        }
        else if (uiState.books != null) {
            val books = uiState.books
            if (books!!.isEmpty()) {
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
