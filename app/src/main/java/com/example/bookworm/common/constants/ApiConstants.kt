package com.example.bookworm.common.constants

const val BASE_URL = "https://www.googleapis.com/books/v1/"

object ApiPaths {
    const val VOLUMES = "volumes"
    const val VOLUME_BY_ID = "volumes/{id}"
    const val BOOKSHELVES = "mylibrary/bookshelves"
    const val SHELF_PARAM = "shelf"
    const val SHELF_BOOKS = "$BOOKSHELVES/{$SHELF_PARAM}/volumes"
    const val ADD_VOLUME = "$BOOKSHELVES/{$SHELF_PARAM}/addVolume"
    const val REMOVE_VOLUME = "$BOOKSHELVES/{$SHELF_PARAM}/removeVolume"
    const val CLEAR_VOLUMES = "$BOOKSHELVES/{$SHELF_PARAM}/clearVolumes"
}

object ApiParams {
    const val QUERY = "q"
    const val API_KEY = "key"
    const val AUTH = "Authorization"
    const val VOLUME_ID = "volumeId"
    const val BOOK_ID = "id"
    const val SHELF = "shelf"
}