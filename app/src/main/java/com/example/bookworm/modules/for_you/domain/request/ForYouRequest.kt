package com.example.bookworm.modules.for_you.domain.request

sealed interface ForYouRequest {
    data class BooksWithCategories(
        val categories: List<String>
    ) : ForYouRequest
}