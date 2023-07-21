package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val publishedDate: String,
    var likedByMe: Boolean = false,
    var likesCount: Int = 0,
    var shareCount: Int = 0,
    var viewCount: Int = 0
)
