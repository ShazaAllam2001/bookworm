package com.example.bookworm.modules.for_you.data.remote.response

import com.example.bookworm.common.api.response.BooksResponse
import com.example.bookworm.modules.for_you.data.model.ForYouBookDataModel
import com.example.bookworm.modules.for_you.data.model.ForYouDataModel

fun BooksResponse.toDataForYou(): ForYouDataModel {
    return ForYouDataModel(
        items.map {
            ForYouBookDataModel(
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