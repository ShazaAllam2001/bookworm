package com.example.bookworm.common.constants

const val BASE_URL = "https://www.googleapis.com/books/v1/"

object ApiPaths {
    const val VOLUMES = "volumes"
    const val VOLUME_BY_ID = "volumes/{id}"
    const val BOOKSHELVES = "mylibrary/bookshelves"
    const val SHELF_BOOKS = "$BOOKSHELVES/{shelf}/volumes"
    const val ADD_VOLUME = "$BOOKSHELVES/{shelf}/addVolume"
    const val REMOVE_VOLUME = "$BOOKSHELVES/{shelf}/removeVolume"
    const val CLEAR_VOLUMES = "$BOOKSHELVES/{shelf}/clearVolumes"
}

object ApiParams {
    const val QUERY = "q"
    const val API_KEY = "key"
    const val AUTH = "Authorization"
    const val VOLUME_ID = "volumeId"
    const val BOOK_ID = "id"
    const val SHELF = "shelf"
}