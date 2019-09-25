package br.com.flying.dutchman.data.remote

import br.com.flying.dutchman.data.MovieEntity
import javax.inject.Inject

open class MovieRemoteEntityMapper @Inject constructor() :
    RemoteEntityMapper<List<Movie>, List<MovieEntity>> {
    override fun mapTo(type: List<MovieEntity>): List<Movie> {
        return type.map {
            Movie(
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

    override fun mapFromRemote(type: List<Movie>): List<MovieEntity> {
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