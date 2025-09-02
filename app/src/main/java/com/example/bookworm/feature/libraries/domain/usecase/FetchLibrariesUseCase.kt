package com.example.bookworm.feature.libraries.domain.usecase

import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class FetchLibrariesUseCase  @Inject constructor(
    private val librarysRepository: LibraryRepository
) {
    suspend operator fun invoke(): LibraryResult {
        return librarysRepository.fetchLibraries()
    }
}