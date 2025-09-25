package com.example.bookworm.feature.books.data.model

import kotlinx.serialization.Serializable


@Serializable
data class BooksResponse(
    val items: List<BookItem>
)
