package com.example.bookworm.modules.for_you.data.source

import com.example.bookworm.modules.for_you.data.remote.response.ForYouResponse
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest

interface ForYouDataSource {
    suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouResponse>
}