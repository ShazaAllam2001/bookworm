package com.example.bookworm.modules.for_you.domain.usecase

import com.example.bookworm.modules.for_you.data.model.toDomain
import com.example.bookworm.modules.for_you.domain.model.ForYouDomainModel
import com.example.bookworm.modules.for_you.domain.repository.ForYouRepository
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

class FetchBooksForYouUseCase @Inject constructor(
    private val booksRepository: ForYouRepository
) {
    suspend operator fun invoke(request: ForYouRequest): Result<ForYouDomainModel> {
        return booksRepository.fetchBooksForYou(request)
            .mapCatching { result -> result.toDomain() }
    }
}