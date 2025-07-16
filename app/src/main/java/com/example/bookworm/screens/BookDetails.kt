package com.example.bookworm.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookworm.data.BookInfo
import com.example.bookworm.data.bookList
import com.example.bookworm.ui.theme.AppTheme


@Composable
fun BookDetails(
    bookInfo: BookInfo
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        Surface(
            modifier = Modifier.padding(5.dp),
            color = AppTheme.colorScheme.onPrimary,
            shape = AppTheme.shape.container,
            border = BorderStroke(1.dp, AppTheme.colorScheme.primary),
            shadowElevation = 1.dp
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                text = bookInfo.title,
                color = AppTheme.colorScheme.primary,
                style = AppTheme.typography.titleLarge
            )
        }
        Image(
            painter = painterResource(bookInfo.image),
            contentDescription = bookInfo.title
        )
        Text("By ${bookInfo.author}")
        Text(stringResource(bookInfo.desc))
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BookDetailsPreview() {
    BookDetails(
        bookInfo = bookList[0]
    )
}
