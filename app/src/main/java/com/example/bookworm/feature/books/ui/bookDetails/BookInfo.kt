package com.example.bookworm.feature.books.ui.bookDetails

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.bookworm.R
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.example.bookworm.feature.books.domain.model.BookItem
import com.example.bookworm.ui.theme.dimens
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
            .padding(MaterialTheme.dimens.paddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier.weight(3f)
                .padding(MaterialTheme.dimens.paddingSmall),
            imageModel = { book.volumeInfo.imageLinks?.thumbnail },
            loading = { LoadingIndicator() },
            failure = { Text("Failed to load image") },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        Text(
            text = book.volumeInfo.title,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(
                R.string.by,
                if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .weight(4f)
                .padding(MaterialTheme.dimens.paddingSmall)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingMedium))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(MaterialTheme.dimens.roundCorner),
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