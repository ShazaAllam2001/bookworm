package com.example.bookworm.modules.explore.data.model

import com.example.bookworm.modules.explore.domain.model.ExploreDomainModel
import com.example.bookworm.modules.explore.domain.model.BookDomainModel

data class ExploreDataModel(
    val items: List<ExploreBookDataModel>
)

data class ExploreBookDataModel(
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

fun ExploreDataModel.toDomain(): ExploreDomainModel {
    return ExploreDomainModel(
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