package com.example.bookworm.feature.libraries.domain.usecase

import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class RemoveBookFromShelfUseCase  @Inject constructor(
    private val librarysRepository: LibraryRepository
) {
    suspend operator fun invoke(shelfId: Int, volumeId: String): ModifyLibraryResult {
        return librarysRepository.removeBookFromShelf(shelfId, volumeId)
    }
}