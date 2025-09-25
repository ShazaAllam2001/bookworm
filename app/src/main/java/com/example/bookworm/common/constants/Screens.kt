package com.example.bookworm.common.constants

enum class Screens(
    val route: String,
    val parameter: String = ""
) {
    SplashScreen(route = "splash"),
    Login(route = "login"),
    Librarys(route = "librarys/{libraryId}", parameter = "libraryId"),
    Books(route = "books/{bookId}", parameter = "bookId")
}