package com.example.bookworm.feature.books.data.model

import kotlinx.serialization.Serializable


@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo,
)
