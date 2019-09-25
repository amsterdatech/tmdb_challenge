package br.com.flying.dutchman.domain

import br.com.flying.dutchman.data.MovieEntity
import com.dutchtechnologies.domain.Movie
import javax.inject.Inject

class MoviesMapper @Inject constructor(
) :
    Mapper<MovieEntity, Movie> {
    override fun mapFromEntity(type: MovieEntity): Movie {
        return Movie(
            type.id,
            type.title,
            type.overview,
            type.posterPath,
            type.backdropPath,
            type.releaseData,
            type.runtime,
            type.voteAverage,
            type.voteCount
        )
    }

    override fun mapToEntity(type: Movie): MovieEntity {
        return MovieEntity(
            type.id,
            type.title,
            type.overview,
            type.posterPath,
            type.backdropPath,
            type.releaseData,
            type.runtime,
            type.voteAverage,
            type.voteCount
        )
    }

}