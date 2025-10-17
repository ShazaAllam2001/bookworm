package com.example.bookworm.modules.explore.domain.repository

import com.example.bookworm.modules.explore.data.model.ExploreDataModel
import com.example.bookworm.modules.explore.domain.request.ExploreRequest

interface ExploreRepository {
    suspend fun fetchBooksExplore(request: ExploreRequest): Result<ExploreDataModel>
    suspend fun searchBooks(request: ExploreRequest): Result<ExploreDataModel>
}