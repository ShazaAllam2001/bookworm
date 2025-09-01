package com.example.bookworm.feature.books.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class BooksResponse(
    val items: List<BookItem>
)

@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val averageRating: Float? = null,
    val ratingsCount: Int? = null,
    val imageLinks: ImageLinks? = null,
    val language: String,
    val previewLink: String
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)
