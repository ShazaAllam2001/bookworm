package com.example.bookworm.feature.books.domain.usecase

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(searchTerms: String): BooksResult {
        return when (val result = booksRepository.searchBooks(searchTerms)) {
            is BooksResult.Success -> {
                BooksResult.Success(result.message)
            }
            is BooksResult.Error -> result
        }
    }
}