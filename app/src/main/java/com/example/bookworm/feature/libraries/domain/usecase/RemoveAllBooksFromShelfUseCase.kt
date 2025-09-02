package com.example.bookworm.feature.libraries.domain.usecase

import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class RemoveAllBooksFromShelfUseCase  @Inject constructor(
    private val librarysRepository: LibraryRepository
) {
    suspend operator fun invoke(shelfId: Int): ModifyLibraryResult {
        return librarysRepository.removeAllBooksFromShelf(shelfId)
    }
}