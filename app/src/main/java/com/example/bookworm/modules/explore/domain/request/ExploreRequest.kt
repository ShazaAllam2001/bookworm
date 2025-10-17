package com.example.bookworm.modules.explore.domain.request

sealed interface ExploreRequest {
    data class BooksWithCategories(
        val categories: List<String>
    ) : ExploreRequest

    data class BooksWithSearchTerms(
        val searchTerms: String
    ) : ExploreRequest
}