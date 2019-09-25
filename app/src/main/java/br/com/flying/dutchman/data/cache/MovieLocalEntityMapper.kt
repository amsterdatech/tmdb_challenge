package br.com.flying.dutchman.data.cache

import br.com.flying.dutchman.data.MovieEntity
import br.com.flying.dutchman.data.cache.entity.Movie
import javax.inject.Inject

open class MovieLocalEntityMapper @Inject constructor() :
    LocalEntityMapper<List<Movie>, List<MovieEntity>> {

    override fun mapFrom(type: List<Movie>): List<MovieEntity> {
        return type.map {
            MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.releaseData,
                it.runtime,
                it.voteAverage,
                it.voteCount
            )
        }
    }

}