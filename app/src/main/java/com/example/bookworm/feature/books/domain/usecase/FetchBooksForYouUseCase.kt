package com.example.bookworm.feature.books.domain.usecase

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import javax.inject.Inject

class FetchBooksForYouUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(categories: List<String>): BooksResult {
        return when (val result = booksRepository.fetchBooksForYou(categories)) {
            is BooksResult.Success -> {
                BooksResult.Success(result.message)
            }
            is BooksResult.Error -> result
        }
    }
}