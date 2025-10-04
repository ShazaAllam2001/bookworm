package com.example.bookworm.modules.for_you.presentation.model

data class ForYouUiModel(
    val items: List<BookUiModel>
)

data class BookUiModel(
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
    val thumbnail: String,
    val onClick: () -> Unit
)
