package com.example.bookworm.modules.bookGrid.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.data.bookList


@Composable
fun BookDetails(
    navController: NavHostController = rememberNavController(),
    bookId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookTopBar(
            navController = navController,
            bookId = bookId
        )
        BookInfo(bookId = bookId)
    }
}

@Composable
fun BookTopBar(
    navController: NavHostController,
    bookId: Int
) {
    Surface(
        modifier = Modifier.padding(5.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_64dp),
                    contentDescription = "Back to books list"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                text = bookList[bookId].title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun BookInfo(
    bookId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(bookList[bookId].image),
            contentDescription = bookList[bookId].title
        )
        Text(
            "By ${bookList[bookId].author}",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            bookList[bookId].desc,
            style = MaterialTheme.typography.bodyMedium
        )
        TextButton(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
            ),
            onClick = {

            }
        ) {
            Text(stringResource(R.string.add_to_shelf))
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BookDetailsPreview() {
    BookDetails(bookId = 0)
}
