package com.example.bookworm.modules.for_you.data.repository

import com.example.bookworm.modules.for_you.data.model.ForYouDataModel
import com.example.bookworm.modules.for_you.data.remote.response.toData
import com.example.bookworm.modules.for_you.data.source.BooksRemoteDataSource
import com.example.bookworm.modules.for_you.domain.repository.BooksRepository
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val forYouDataSource: BooksRemoteDataSource
) : BooksRepository {

    override suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouDataModel> {
        return forYouDataSource.fetchBooksForYou(request)
            .mapCatching { result -> result.toData() }
    }

}