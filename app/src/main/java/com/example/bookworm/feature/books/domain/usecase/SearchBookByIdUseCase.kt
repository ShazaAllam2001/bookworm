package com.example.bookworm.feature.books.domain.usecase

import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import javax.inject.Inject

class SearchBookByIdUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(bookId: String): BookIdResult {
        return when (val result = booksRepository.searchBookById(bookId)) {
            is BookIdResult.Success -> {
                BookIdResult.Success(result.message)
            }
            is BookIdResult.Error -> result
        }
    }
}