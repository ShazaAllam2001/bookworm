package com.example.bookworm.modules.book_grid.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.bookworm.modules.for_you.presentation.model.BookUiModel
import com.example.bookworm.ui.theme.dimens
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookCover(
    modifier: Modifier = Modifier,
    bookRatio: Float = 0.8f,
    book: BookUiModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(bookRatio)
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
                imageModel = { book.smallThumbnail },
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