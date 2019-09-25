package com.dutchtechnologies.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseData: String,
    val runtime: Int,
    val voteAverage: Float,
    val voteCount: Int,
    var isFavourite: Boolean = false,
    var inWatchList: Boolean = false
)
