package com.example.bookworm.feature.books.ui.foryou

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.feature.books.ui.bookGrid.BookGrid
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.example.bookworm.feature.books.ui.BookViewModel


@Composable
fun ForYou(
    bookViewModel: BookViewModel,
    userViewModel: UserViewModel,
    navController: NavHostController = rememberNavController()
) {
    var name by rememberSaveable { mutableStateOf("") }
    val uiState by bookViewModel.uiState.collectAsState()

    LaunchedEffect(userViewModel.userData) {
        val uid = userViewModel.readPreferences().uid
        if (userViewModel.userData == null) {
            userViewModel.readUser(uid)
        }
        else {
            name = userViewModel.userData!!.displayName
            bookViewModel.fetchBooksForYou(userViewModel.userData!!.categories)
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
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = buildAnnotatedString {
                append(stringResource(R.string.for_name))
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(" $name")
                }
            },
            style = MaterialTheme.typography.titleLarge
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
                        .padding(15.dp),
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
