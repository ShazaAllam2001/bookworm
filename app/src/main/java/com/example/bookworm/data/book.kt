package com.example.bookworm.data

import com.example.bookworm.R

var bookList = listOf(
    BookInfo(
        title = "Twilight",
        author = "Stephenie Meyer",
        desc = R.string.twilight,
        image = R.drawable.twilight
    ),
    BookInfo(
        title = "The Nightingale",
        author = "Kristin Hannah",
        desc = R.string.nightingale,
        image = R.drawable.the_nightingale
    ),
    BookInfo(
        title = "The Fault in Our Stars",
        author = "John Green",
        desc = R.string.fault_on_our_stars,
        image = R.drawable.the_fault_in_our_stars
    ),
    BookInfo(
        title = "Hunger Games",
        author = "Suzanne Collins",
        desc = R.string.hunger_games,
        image = R.drawable.hunger_games
    ),
    BookInfo(
        title = "Great Gatsby",
        author = "F. Scott Fitzgerald",
        desc = R.string.great_gasby,
        image = R.drawable.great_gasby
    ),
    BookInfo(
        title = "Double Sin",
        author = "Agatha Christie",
        desc = R.string.double_sin,
        image = R.drawable.double_sin
    )
)

data class BookInfo(
    val title: String,
    val author: String,
    val desc: Int,
    val image: Int
)