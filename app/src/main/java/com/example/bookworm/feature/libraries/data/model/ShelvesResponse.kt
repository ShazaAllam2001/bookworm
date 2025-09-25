package com.example.bookworm.feature.libraries.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ShelvesResponse(
    val items: List<Shelf>
)