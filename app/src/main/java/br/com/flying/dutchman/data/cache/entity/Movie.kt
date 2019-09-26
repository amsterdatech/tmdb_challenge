package br.com.flying.dutchman.data.cache.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    var id: Int,
    var title: String = "",
    var overview: String = ""
)