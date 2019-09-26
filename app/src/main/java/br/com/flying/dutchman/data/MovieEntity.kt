package br.com.flying.dutchman.data

data class MovieEntity(
    var id: Int = 0,
    var title: String = "",
    var overview: String = "",
    var posterPath: String = "",
    var backdropPath: String = "",
    var releaseData: String = "",
    var runtime: Int = 0,
    var voteAverage: Float = 0.0f,
    var voteCount: Int = 0,
    var isFavourite: Boolean = false,
    var inWatchList: Boolean = false
)