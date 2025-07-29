package com.example.bookworm.modules.myLibrary.data

import com.example.bookworm.R

var librariesList = listOf(
    LibraryInfo(
        icon = R.drawable.bookmark_64dp,
        name = R.string.want_to_read,
        numberOfBooks = 2,
        books = listOf(0, 1)
    ),
    LibraryInfo(
        icon = R.drawable.import_contacts_64dp,
        name = R.string.currently_reading,
        numberOfBooks = 5,
        books = listOf(0, 1, 2, 3, 4)
    ),
    LibraryInfo(
        icon = R.drawable.check_64dp,
        name = R.string.read,
        numberOfBooks = 2,
        books = listOf(0, 1)
    ),
    LibraryInfo(
        icon = R.drawable.favorite_64dp,
        name = R.string.favourites,
        numberOfBooks = 5,
        books = listOf(0, 1, 2, 3, 4)
    ),
    LibraryInfo(
        icon = R.drawable.shopping_cart_64dp,
        name = R.string.to_buy,
        numberOfBooks = 3,
        books = listOf(0, 1, 2)
    ),
    LibraryInfo(
        icon = R.drawable.book_2_64dp,
        name = R.string.borrowed,
        numberOfBooks = 6,
        books = listOf(0, 1, 2, 3, 4, 5)
    ),
    LibraryInfo(
        icon = R.drawable.format_list_bulleted_64dp,
        name = R.string.wishlist,
        numberOfBooks = 4,
        books = listOf(0, 1, 2, 3)
    ),
)

data class LibraryInfo(
    val icon: Int,
    val name: Int,
    val numberOfBooks: Int,
    val books: List<Int>
)