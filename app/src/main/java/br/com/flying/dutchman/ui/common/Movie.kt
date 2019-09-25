package br.com.flying.dutchman.ui.common

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("backdrop_path") val backdrop: String,
    @SerializedName("poster_path") val posterPath: String,
    val isFavourite: Boolean = false
)