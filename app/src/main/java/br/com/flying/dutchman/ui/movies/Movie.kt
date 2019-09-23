package br.com.flying.dutchman.ui.movies

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    @SerializedName("backdrop_path") val backdrop: String,
    @SerializedName("poster_path") val posterPath: String,
    val isFavourite: Boolean = false
)