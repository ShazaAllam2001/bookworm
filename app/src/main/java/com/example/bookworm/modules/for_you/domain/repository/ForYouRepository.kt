package com.example.bookworm.modules.for_you.domain.repository

import com.example.bookworm.modules.for_you.data.model.ForYouDataModel
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest

interface ForYouRepository {
    suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouDataModel>
}