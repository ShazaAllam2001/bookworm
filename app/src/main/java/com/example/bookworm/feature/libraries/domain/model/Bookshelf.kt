package com.example.bookworm.feature.libraries.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ShelvesResponse(
    val items: List<Shelf>
)

@Serializable
data class Shelf(
    val id: Int,
    val selfLink: String,
    val title: String,
    val access: String,
    val updated: String,
    val created: String,
    val volumeCount: Int,
    val volumesLastUpdated: String
)
