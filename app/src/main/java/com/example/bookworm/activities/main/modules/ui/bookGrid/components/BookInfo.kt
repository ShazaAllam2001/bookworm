package com.example.bookworm.activities.main.modules.ui.bookGrid.components

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.viewModel.books.BookItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun BookInfo(
    book: BookItem,
    onShowDialogChange: (Boolean) -> Unit
) {
    val bodyStyle = MaterialTheme.typography.bodyMedium
    val bodyColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            imageModel = { book.volumeInfo.imageLinks?.thumbnail },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        Text(
            stringResource(
                R.string.by,
                if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
            ),
            style = MaterialTheme.typography.labelLarge
        )
        Row {
            Icon(
                painter = painterResource(R.drawable.baseline_star_rate_48),
                contentDescription = "Rating"
            )
            Text(
                buildAnnotatedString {
                    append("Average Rating of ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("${book.volumeInfo.averageRating ?: "_"}")
                    }
                    append(" from ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("${book.volumeInfo.ratingsCount ?: "_"}")
                    }
                    append(" Reviewers")
                },
                style = MaterialTheme.typography.labelMedium
            )
        }
        AndroidView(
            factory = { context ->
                TextView(context)
            },
            update = { textView ->
                textView.text = HtmlCompat.fromHtml(
                    book.volumeInfo.description?: "",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                textView.textSize = bodyStyle.fontSize.value
                textView.setTextColor(bodyColor.toArgb())
            }
        )
        TextButton(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
            ),
            onClick = {
                onShowDialogChange(true)
            }
        ) {
            Text(stringResource(R.string.add_to_shelf))
        }
    }
}