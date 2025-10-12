package com.example.bookworm.feature.libraries.domain.usecase

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class GetLibraryBooksUseCase  @Inject constructor(
    private val librarysRepository: LibraryRepository
) {
    suspend operator fun invoke(shelfId: Int): BooksResult {
        return librarysRepository.getLibraryBooks(shelfId)
    }
}