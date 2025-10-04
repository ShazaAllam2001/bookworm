package com.example.bookworm.modules.for_you.data.model

import com.example.bookworm.modules.for_you.domain.model.BookDomainModel
import com.example.bookworm.modules.for_you.domain.model.ForYouDomainModel

data class ForYouDataModel(
    val items: List<BookDataModel>
)

data class BookDataModel(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val smallThumbnail: String,
    val thumbnail: String
)

fun ForYouDataModel.toDomain(): ForYouDomainModel {
    return ForYouDomainModel(
        items.map {
            BookDomainModel(
                id = it.id,
                title = it.title,
                subtitle = it.subtitle,
                authors = it.authors,
                publisher = it.publisher,
                publishedDate = it.publishedDate,
                description = it.description,
                pageCount = it.pageCount,
                categories = it.categories,
                smallThumbnail = it.smallThumbnail,
                thumbnail = it.thumbnail
            )
        }
    )
}