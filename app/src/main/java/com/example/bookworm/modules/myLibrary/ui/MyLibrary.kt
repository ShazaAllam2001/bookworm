package com.example.bookworm.modules.myLibrary.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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
import com.example.bookworm.modules.myLibrary.ui.components.MyLibraryRow
import com.example.bookworm.modules.myLibrary.data.librarysList


@Composable
fun MyLibrary(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(
            modifier = Modifier.padding(15.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.my_library),
                style = MaterialTheme.typography.titleLarge
            )
        }
        LibrariesList(navController = navController)
    }
}

@Composable
fun LibrariesList(
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ) {
        items(librarysList.size) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("librarys/${index}")
                }
            ) {
                MyLibraryRow(
                    icon = librarysList[index].icon,
                    name = librarysList[index].name,
                    numberOfBooks = librarysList[index].numberOfBooks,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyLibraryPreview() {
    MyLibrary()
}
