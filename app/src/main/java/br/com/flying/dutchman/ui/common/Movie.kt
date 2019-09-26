package br.com.flying.dutchman.ui.common

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop: String,
    val posterPath: String,
    val releaseData: String,
    val runtime: Int,
    val voteAverage: Float,
    val voteCount: Int,
    var isFavourite: Boolean = false,
    var inWatchList: Boolean = false
)