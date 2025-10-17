package com.example.bookworm.modules.explore.domain.usecase

import com.example.bookworm.modules.explore.data.model.toDomain
import com.example.bookworm.modules.explore.domain.model.ExploreDomainModel
import com.example.bookworm.modules.explore.domain.repository.ExploreRepository
import com.example.bookworm.modules.explore.domain.request.ExploreRequest
import javax.inject.Inject

class FetchBooksExploreUseCase @Inject constructor(
    private val booksRepository: ExploreRepository
) {
    suspend operator fun invoke(request: ExploreRequest): Result<ExploreDomainModel> {
        return booksRepository.fetchBooksExplore(request)
            .mapCatching { result -> result.toDomain() }
    }
}