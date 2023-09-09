package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val publishedDate: String,
    var likedByMe: Boolean = false,
    var likesCount: Long = 0,
    var shareCount: Long = 0,
    var viewCount: Long = 0
)
