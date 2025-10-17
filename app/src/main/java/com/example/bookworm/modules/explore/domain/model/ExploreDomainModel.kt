package com.example.bookworm.modules.explore.domain.model

import com.example.bookworm.common.ui.model.BookUiModel


data class ExploreDomainModel(
    val items: List<BookDomainModel>
)

data class BookDomainModel(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val smallThumbnail: String,
    val thumbnail: String
)

fun BookDomainModel.toUI(onClick: () -> Unit): BookUiModel {
    return BookUiModel(
        id = id,
        title = title,
        subtitle = subtitle,
        authors = authors,
        publisher = publisher,
        publishedDate = publishedDate,
        description = description,
        pageCount = pageCount,
        categories = categories,
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail,
        onClick = onClick
    )
}