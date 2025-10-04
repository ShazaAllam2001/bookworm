package com.example.bookworm.modules.for_you.data.repository

import com.example.bookworm.modules.for_you.data.model.ForYouDataModel
import com.example.bookworm.modules.for_you.data.remote.response.toData
import com.example.bookworm.modules.for_you.data.source.ForYouDataSource
import com.example.bookworm.modules.for_you.domain.repository.ForYouRepository
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

class ForYouRepositoryImpl @Inject constructor(
    private val forYouDataSource: ForYouDataSource
) : ForYouRepository {

    override suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouDataModel> {
        return forYouDataSource.fetchBooksForYou(request)
            .mapCatching { result -> result.toData() }
    }

}