package com.example.bookworm.modules.explore.data.repository

import com.example.bookworm.modules.explore.data.model.ExploreDataModel
import com.example.bookworm.modules.explore.data.remote.response.toDataExplore
import com.example.bookworm.modules.explore.data.source.ExploreBooksRemoteDataSource
import com.example.bookworm.modules.explore.domain.repository.ExploreRepository
import com.example.bookworm.modules.explore.domain.request.ExploreRequest
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val exploreDataSource: ExploreBooksRemoteDataSource
) : ExploreRepository {

    override suspend fun fetchBooksExplore(request: ExploreRequest): Result<ExploreDataModel> {
        return exploreDataSource.fetchBooksExplore(request)
            .mapCatching { result -> result.toDataExplore() }
    }

    override suspend fun searchBooks(request: ExploreRequest): Result<ExploreDataModel> {
        return exploreDataSource.searchBooks(request)
            .mapCatching { result -> result.toDataExplore() }
    }

}