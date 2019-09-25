package com.dutchtechnologies.domain

data class Movie(
    val id: Long,
    val title:String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseData: String,
    val runtime: Int,
    val voteAverage: Float,
    val voteCount: Int
)
