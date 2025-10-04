package com.example.bookworm.modules.for_you.data.remote.response

import com.example.bookworm.modules.for_you.data.model.BookDataModel
import com.example.bookworm.modules.for_you.data.model.ForYouDataModel
import kotlinx.serialization.Serializable


@Serializable
data class ForYouResponse(
    val items: List<BookItem>
)

@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)


fun ForYouResponse.toData(): ForYouDataModel {
    return ForYouDataModel(
        items.map {
            BookDataModel(
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