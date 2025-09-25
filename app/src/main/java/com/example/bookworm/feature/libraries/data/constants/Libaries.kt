package com.example.bookworm.feature.libraries.data.constants

import com.example.bookworm.R

val LibrariesMap = mapOf(
    0 to Pair(R.drawable.favorites_24, LibraryType.ADD_REMOVE), // Favorites
    1 to Pair(R.drawable.shopping_cart_24, LibraryType.VIEW_ONLY), // Purchased
    2 to Pair(R.drawable.bookmark_24_outlined, LibraryType.ADD_REMOVE), // ToRead
    3 to Pair(R.drawable.import_contacts_24, LibraryType.ADD_REMOVE), // ReadingNow
    4 to Pair(R.drawable.check_24, LibraryType.ADD_REMOVE), // HaveRead
    5 to Pair(R.drawable.book_2_24, LibraryType.VIEW_ONLY), // Reviewed
    6 to Pair(R.drawable.book_24, LibraryType.VIEW_ONLY), // RecentlyViewed
    7 to Pair(R.drawable.format_list_bulleted_24, LibraryType.REMOVE_ONLY),// MyeBooks
    8 to Pair(R.drawable.person_24, LibraryType.VIEW_OUTSIDE_LIBRARY), // BooksForYou
    9 to Pair(R.drawable.history_24, LibraryType.VIEW_ONLY) // Browsing History
)

enum class LibraryType {
    REMOVE_ONLY,
    ADD_REMOVE,
    VIEW_ONLY,
    VIEW_OUTSIDE_LIBRARY
}