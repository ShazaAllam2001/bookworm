package com.example.bookworm.feature.books.data.model

import kotlinx.serialization.Serializable


@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)