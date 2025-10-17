package com.example.bookworm.modules.for_you.data.repository

import com.example.bookworm.modules.for_you.data.model.ForYouDataModel
import com.example.bookworm.modules.for_you.data.remote.response.toDataForYou
import com.example.bookworm.modules.for_you.data.source.ForYouBooksRemoteDataSource
import com.example.bookworm.modules.for_you.domain.repository.ForYouRepository
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

class ForYouRepositoryImpl @Inject constructor(
    private val forYouDataSource: ForYouBooksRemoteDataSource
) : ForYouRepository {

    override suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouDataModel> {
        return forYouDataSource.fetchBooksForYou(request)
            .mapCatching { result -> result.toDataForYou() }
    }

}