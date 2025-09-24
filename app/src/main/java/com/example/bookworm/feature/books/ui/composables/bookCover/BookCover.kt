package com.example.bookworm.feature.books.ui.composables.bookCover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.ui.theme.dimens
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookCover(
    modifier: Modifier = Modifier,
    book: BookItem
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .shadow(
                    elevation = MaterialTheme.dimens.shadowElevation,
                    shape = RoundedCornerShape(MaterialTheme.dimens.shadowElevation),
                    clip = false
                ),
            contentAlignment = Alignment.Center
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxSize(),
                imageModel = { book.volumeInfo.imageLinks?.smallThumbnail },
                loading = { LoadingIndicator() },
                failure = { Text("Failed to load image") },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center
                )
            )
        }
    }
}