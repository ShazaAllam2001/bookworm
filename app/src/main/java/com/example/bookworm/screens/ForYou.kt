package com.example.bookworm.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.components.Book
import com.example.bookworm.data.bookList


@Composable
fun ForYou(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shadowElevation = 1.dp
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = stringResource(R.string.for_you),
                style = MaterialTheme.typography.titleLarge
            )
        }
        BookGrid(navController = navController)
    }
}

@Composable
fun BookGrid(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = 12.dp,
            bottom = 12.dp,
            start = 16.dp,
            end = 16.dp
        ),
        content = {
            items(bookList.size) { index ->
                Book(
                    modifier = Modifier.aspectRatio(0.7f),
                    navController = navController,
                    bookId = index
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ForYouPreview() {
    ForYou()
}
