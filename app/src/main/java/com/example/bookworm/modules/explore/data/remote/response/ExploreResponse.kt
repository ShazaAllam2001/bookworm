package com.example.bookworm.modules.explore.data.remote.response

import com.example.bookworm.common.api.response.BooksResponse
import com.example.bookworm.modules.explore.data.model.ExploreBookDataModel
import com.example.bookworm.modules.explore.data.model.ExploreDataModel


fun BooksResponse.toDataExplore(): ExploreDataModel {
    return ExploreDataModel(
        items.map {
            ExploreBookDataModel(
                id = it.id,
                title = it.volumeInfo.title,
                subtitle = it.volumeInfo.subtitle,
                authors = it.volumeInfo.authors,
                publisher = it.volumeInfo.publisher,
                publishedDate = it.volumeInfo.publishedDate,
                description = it.volumeInfo.description,
                pageCount = it.volumeInfo.pageCount,
                categories = it.volumeInfo.categories,
                smallThumbnail = it.volumeInfo.imageLinks?.smallThumbnail ?: "",
                thumbnail = it.volumeInfo.imageLinks?.thumbnail ?: ""
            )
        }
    )
}