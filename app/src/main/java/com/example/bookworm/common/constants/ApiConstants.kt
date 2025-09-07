package com.example.bookworm.common.constants

const val BASE_URL = "https://www.googleapis.com/books/v1/"

object ApiPaths {
    const val VOLUMES = "volumes"
    const val VOLUME_BY_ID = "volumes/{id}"
    const val BOOKSHELVES = "mylibrary/bookshelves"
    const val SHELF_BOOKS = "mylibrary/bookshelves/{shelf}/volumes"
    const val ADD_VOLUME = "mylibrary/bookshelves/{shelf}/addVolume"
    const val REMOVE_VOLUME = "mylibrary/bookshelves/{shelf}/removeVolume"
    const val CLEAR_VOLUMES = "mylibrary/bookshelves/{shelf}/clearVolumes"
}

object ApiParams {
    const val QUERY = "q"
    const val API_KEY = "key"
    const val AUTH = "Authorization"
    const val VOLUME_ID = "volumeId"
    const val BOOK_ID = "id"
    const val SHELF = "shelf"
}