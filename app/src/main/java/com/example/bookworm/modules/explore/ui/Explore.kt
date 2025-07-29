package com.example.bookworm.modules.explore.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.ui.BookGrid
import com.example.bookworm.modules.viewModel.BookModel


@Composable
fun Explore(
    viewModel: BookModel = BookModel(),
    navController: NavHostController = rememberNavController()
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

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
            text = stringResource(R.string.explore),
            style = MaterialTheme.typography.titleLarge
        )
        SearchField(
            searchText = searchText,
            onChangeText = { searchText = it }
        )
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            BookGrid(
                navController = navController,
                bookList = books
            )
        }
    }
}

@Composable
fun SearchField(
    searchText: String,
    onChangeText: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp),
        value = searchText,
        onValueChange = onChangeText,
        placeholder = {
            Text(
                stringResource(R.string.search_for_books),
                style = MaterialTheme.typography.titleSmall
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp)
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ExplorePreview() {
    Explore()
}
