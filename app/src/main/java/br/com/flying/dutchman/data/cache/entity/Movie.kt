package br.com.flying.dutchman.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
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